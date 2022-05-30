from bs4 import BeautifulSoup
import selenium
import requests
from time import sleep


id_f=0
lstFilms=[]
lstPers=[]
lstGenre=[]


#fonction de récupération des liens (string)
def findLinks(soup):
    res=[]
    for link in soup.find_all("a",{"class":"title-list-grid__item--link"}):
        res.append("https://www.justwatch.com"+link["href"])
    return res


#fonction de récupération des réalisateurs (string)
def getReal(soup):
    return soup.find_all("a",class_="title-credit-name")[0].text


#fonctions de récupération du cast (list)
def getCast(soup):
    res=[]
    i=0
    for cast in soup.find_all("a",class_="title-credit-name"):
        res.append(cast.text)
        i+=1
        if i>10:  #limitation à 10 acteurs par films
            break
    res.pop(0)
    res.pop(0)
    return res

#fonction de récupération du lien des images des films (string)
def getImage(soup):
    res=""
    line = soup.find_all("source",{"type":"image/jpeg"})[3]["data-srcset"]
    for car in line:
        if car ==",":
            return res
        else:
            res+=car

#fonction de récupération des genres (liste)
def getGenre(soup):
    res=[]
    line=soup.find_all("div",{"class":"detail-infos__value"})[1].text
    lstGenre=list(line.split(" , "))
    lstGenre[len(lstGenre)-1]=lstGenre[len(lstGenre)-1][:-1]
    return lstGenre

#fonction de récupération du résumé (string)
def getResume(soup):
    line=soup.find("p",{"class":"text-wrap-pre-line mt-0"}).text
    for i in range(len(line)):
        if line[i]=='"':
            return None
    return line

#fonction de récupération des titres (string)
def getTitre(soup):
    return soup.find("h1").text[1:-1] #contient des espaces au début et à la fin

#fonction de récupération des années de sortie (string)
def getAnnee(soup):
    return soup.find("span",{"class":"text-muted"}).text[2:-2]

#fonction de récupération des durées des films
def getDuree(soup):
    return soup.find_all("div",{"class":"detail-infos__value"})[2].text

#vérification que le genre n'a pas déjà été créée
def isAldreadyAddGenre(soup,genre):
    i=0
    for g in lstGenre:
        if g==genre:
            return i+1
        i+=1
    return None

#vérification que la personne n'a pas déjà été créée 
def isAldreadyAddPers(soup,pers):
    i=0
    for p in lstPers:
        if p==pers:
            return i
        i+=1
    return None

#fonction d'initialisation et d'écriture des requetes SQL pour un lien de film
def initTxt(link):
    global id_f
    result=requests.get(link)
    soup = BeautifulSoup(result.text, 'html.parser')
    with open('films.txt', 'a',encoding="utf-8") as f:
        try:
            f.writelines('\nINSERT INTO Film(titre,resume,annee_sortie,duree,image) VALUES ("'+getTitre(soup)+'","'+getResume(soup)+'","'+getAnnee(soup)+'","'+getDuree(soup)+'","'+getImage(soup)+'");\n')
            lstFilms.append(link)
            idP=isAldreadyAddPers(soup, getReal(soup))
            if idP==None:
                lstPers.append(getReal(soup))
                prenom=getReal(soup).split(" ")[1]
                nom=getReal(soup).split(" ")[2]
                f.writelines('INSERT IGNORE INTO Personne(nom,prenom) VALUES ("'+nom+'","'+prenom+'");\n')
                f.writelines("INSERT INTO realise(id_Film, id_Personne) VALUES ("+str(len(lstFilms))+","+str(len(lstPers))+");\n")
            else:
                f.writelines("INSERT INTO realise(id_Film, id_Personne) VALUES ("+str(len(lstFilms))+","+str(idP)+");\n")
            for cast in getCast(soup):
                idP=isAldreadyAddPers(soup, cast)
                if idP== None:
                    lstPers.append(cast)
                    prenom=cast.split(" ")[1]
                    nom=cast.split(" ")[2]
                    f.writelines('INSERT IGNORE INTO Personne(nom,prenom) VALUES ("'+nom+'","'+prenom+'");\n')
                    f.writelines("INSERT INTO joue(id_Film, id_Personne) VALUES ("+str(len(lstFilms))+","+str(len(lstPers))+");\n")
                else:
                    f.writelines("INSERT INTO joue(id_Film, id_Personne) VALUES ("+str(len(lstFilms))+","+str(idP)+");\n")
            for genre in getGenre(soup):
                idG=isAldreadyAddGenre(soup, genre)
                if idG!=None:
                    f.writelines("INSERT INTO genres2films(id_Genre,id_Film) VALUES ("+str(idG)+","+str(len(lstFilms))+");\n")
                else:
                    lstGenre.append(genre)
                    f.writelines("INSERT INTO Genre(id_Genre,genre) VALUES ("+str(len(lstGenre))+",'"+genre+"');\n")
                    f.writelines("INSERT INTO genres2films(id_Genre,id_Film) VALUES ("+str(len(lstGenre))+","+str(len(lstFilms))+");\n")

        except:
            print("error")
        f.close()
    
#écriture des requêtes à partir du lien du site contenant les liens des films
def initTxtGlobal(soup):
    global id_f
    res=[]
    for link in findLinks(soup):
        print(link)
        initTxt(link)


#liste des liens JustWatch avec le filtre des années
urls=["https://www.justwatch.com/fr/films?release_year_from=2022","https://www.justwatch.com/fr/films?release_year_from=2021&release_year_until=2021","https://www.justwatch.com/fr/films?release_year_from=2020&release_year_until=2020","https://www.justwatch.com/fr/films?release_year_from=2019&release_year_until=2019","https://www.justwatch.com/fr/films?release_year_from=2018&release_year_until=2018","https://www.justwatch.com/fr/films?release_year_from=2017&release_year_until=2017","https://www.justwatch.com/fr/films?release_year_from=2016&release_year_until=2016","https://www.justwatch.com/fr/films?release_year_from=2015&release_year_until=2015"]
with open('films.txt', 'a',encoding="utf-8") as f:
     f.writelines('TRUNCATE TABLE Film;\n')
     f.writelines('TRUNCATE TABLE Genre;\n')
     f.writelines('TRUNCATE TABLE genres2films;\n')
     f.writelines('TRUNCATE TABLE joue;\n')
     f.writelines('TRUNCATE TABLE Personne;\n')
     f.writelines('TRUNCATE TABLE realise;\n')
     f.close()

#écriture pour tous les liens JustWatch 
for url in urls:
    result=requests.get(url)
    soup = BeautifulSoup(result.text, 'html.parser')
    initTxtGlobal(soup)

