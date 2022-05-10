from bs4 import BeautifulSoup
import selenium
import requests

url="https://www.justwatch.com/fr/films"
urlfilm="https://www.justwatch.com/fr/film/les-animaux-fantastiques"
result=requests.get(urlfilm)
soup = BeautifulSoup(result.text, 'html.parser')


def findLinks():
    res=[]
    for link in soup.find_all("a",{"class":"title-list-grid__item--link"}):
        res.append("https://www.justwatch.comlink"+link["href"])
    return res




def getReal(soup):
    return soup.find_all("a",class_="title-credit-name")[0].text


def getCast(soup):
    res=[]
    for cast in soup.find_all("a",class_="title-credit-name"):
        res.append(cast.text)
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
    return line

def getTitre(soup):
    return soup.find("h1").text #contient des espaces au début et à la fin

def getAnnee(soup):
    return soup.find("span",{"class":"text-muted"}).text[2:-2]

def getDuree(soup):
    return soup.find_all("div",{"class":"detail-infos__value"})[2].text


def initTxt(link):
    result=requests.get(link)
    soup = BeautifulSoup(result.text, 'html.parser')
    with open('films.txt', 'w',encoding="") as f:
        f.write("INSERT INTO Film(titre,resume,annee_sortie,duree) VALUES ('"+getTitre(soup)+"','"+getResume(soup)+"','"+getAnnee(soup)+"','"+getDuree(soup))

def initTxtGlobal():
    with open('films.txt', 'w') as f:
        for link in findLinks():
            result=requests.get(link)
            soup = BeautifulSoup(result.text, 'html.parser')
            f.write(getTitre(link))

#print(initTxt(urlfilm))