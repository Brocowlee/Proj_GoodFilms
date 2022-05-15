from bs4 import BeautifulSoup
import selenium
import requests
from time import sleep


id_f=0
lstFilms=[]
lstPers=[]
lstGenre=[]

def findLinks(soup):
    res=[]
    for link in soup.find_all("a",{"class":"title-list-grid__item--link"}):
        res.append("https://www.justwatch.com"+link["href"])
    return res

# def findLinks(soup):
#     res=[]
#     for link in soup.find_all("a",{"class":"html-attribute-value html-external-link"}):
#         try:
#             if link["href"][29]=="f" and link["href"][30]=="i" and link["href"][31]=="l" and link["href"][32]=="m":
#                 res.append(link["href"])
#         except:
#             pass
#     res.pop(0)
#     res.pop(0)
#     res.pop(0)
#     res.pop(0)
#     return res

def getReal(soup):
    return soup.find_all("a",class_="title-credit-name")[0].text


def getCast(soup):
    res=[]
    i=0
    for cast in soup.find_all("a",class_="title-credit-name"):
        res.append(cast.text)
        i+=1
        if i>10:
            break
    res.pop(0)
    res.pop(0)
    return res


def getImage(soup):
    res=""
    line = soup.find_all("source",{"type":"image/jpeg"})[3]["data-srcset"]
    for car in line:
        if car ==",":
            return res
        else:
            res+=car

def getGenre(soup):
    res=[]
    line=soup.find_all("div",{"class":"detail-infos__value"})[1].text
    lstGenre=list(line.split(" , "))
    lstGenre[len(lstGenre)-1]=lstGenre[len(lstGenre)-1][:-1]
    return lstGenre

def getResume(soup):
    line=soup.find("p",{"class":"text-wrap-pre-line mt-0"}).text
    for i in range(len(line)):
        if line[i]=='"':
            return None
    return line

def getTitre(soup):
    return soup.find("h1").text[1:-1] #contient des espaces au début et à la fin

def getAnnee(soup):
    return soup.find("span",{"class":"text-muted"}).text[2:-2]

def getDuree(soup):
    return soup.find_all("div",{"class":"detail-infos__value"})[2].text

def isAldreadyAddGenre(soup,genre):
    i=0
    for g in lstGenre:
        if g==genre:
            return i
        i+=1
    return None

def isAldreadyAddPers(soup,pers):
    i=0
    for p in lstPers:
        if p==pers:
            return i
        i+=1
    return None

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
                f.writelines("INSERT INTO realise(id_f, id_p) VALUES ("+str(len(lstFilms))+","+str(len(lstPers))+");\n")
            else:
                f.writelines("INSERT INTO realise(id_f, id_p) VALUES ("+str(len(lstFilms))+","+str(idP)+");\n")
            for cast in getCast(soup):
                idP=isAldreadyAddPers(soup, cast)
                if idP== None:
                    lstPers.append(cast)
                    prenom=cast.split(" ")[1]
                    nom=cast.split(" ")[2]
                    f.writelines('INSERT IGNORE INTO Personne(nom,prenom) VALUES ("'+nom+'","'+prenom+'");\n')
                    f.writelines("INSERT INTO joue(id_f, id_p) VALUES ("+str(len(lstFilms))+","+str(len(lstPers))+");\n")
                else:
                    f.writelines("INSERT INTO joue(id_f, id_p) VALUES ("+str(len(lstFilms))+","+str(idP)+");\n")
            for genre in getGenre(soup):
                idG=isAldreadyAddGenre(soup, genre)
                if idG!=None:
                    f.writelines("INSERT INTO genres2films(id_g,id_f) VALUES ("+str(idG)+","+str(len(lstFilms))+");\n")
                else:
                    lstGenre.append(genre)
                    f.writelines("INSERT INTO Genre(id_g,genre) VALUES ("+str(len(lstGenre))+",'"+genre+"');\n")
                    f.writelines("INSERT INTO genres2films(id_g,id_f) VALUES ("+str(len(lstGenre))+","+str(len(lstFilms))+");\n")

        except:
            print("error")
        f.close()
    

def initTxtGlobal(soup):
    global id_f
    res=[]
    for link in findLinks(soup):
        print(link)
        initTxt(link)



# with open("www.justwatch.com_fr_films.html",encoding="utf-8") as fp:
#     soup = BeautifulSoup(fp, 'html.parser')
#     fp.close()

#print(initTxt(urlfilm))
#initTxtGlobal(soup)

urls=["https://www.justwatch.com/fr/films?release_year_from=2022","https://www.justwatch.com/fr/films?release_year_from=2021&release_year_until=2021","https://www.justwatch.com/fr/films?release_year_from=2020&release_year_until=2020","https://www.justwatch.com/fr/films?release_year_from=2019&release_year_until=2019","https://www.justwatch.com/fr/films?release_year_from=2018&release_year_until=2018","https://www.justwatch.com/fr/films?release_year_from=2017&release_year_until=2017","https://www.justwatch.com/fr/films?release_year_from=2016&release_year_until=2016","https://www.justwatch.com/fr/films?release_year_from=2015&release_year_until=2015"]
url="https://www.justwatch.com/fr/film/fantastic-beasts-3"
with open('films.txt', 'a',encoding="utf-8") as f:
     f.writelines('TRUNCATE TABLE Film;\n')
     f.writelines('TRUNCATE TABLE Genre;\n')
     f.writelines('TRUNCATE TABLE genres2films;\n')
     f.writelines('TRUNCATE TABLE joue;\n')
     f.writelines('TRUNCATE TABLE Personne;\n')
     f.writelines('TRUNCATE TABLE realise;\n')
     f.close()

for url in urls:
    result=requests.get(url)
    soup = BeautifulSoup(result.text, 'html.parser')
    initTxtGlobal(soup)


# result=requests.get(url)
# soup = BeautifulSoup(result.text, 'html.parser')
# #print(getResume(soup))
# print(getCast(soup))#base >