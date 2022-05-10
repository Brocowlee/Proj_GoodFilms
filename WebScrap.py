from bs4 import BeautifulSoup
import selenium
import requests

url="https://www.justwatch.com/fr/films"
urlfilm="https://www.justwatch.com/fr/film/spider-man-no-way-home"
result=requests.get(urlfilm)
soup = BeautifulSoup(result.text, 'html.parser')


def findLink(text):
    cross=False
    res=""
    lastcar=""
    for car in text:
        if car=='"' and cross==True:
            return res
        elif car=='/' and lastcar=='"' and cross==False:
            cross=True
        elif cross==True:
            res+=car
        lastcar=car

# for e in soup.find_all("a",class_="title-list-grid__item--link"):
#     print(findLink(str(e)))

def getReal(link):
    result=requests.get(link)
    soup = BeautifulSoup(result.text, 'html.parser')
    res=""
    cross=False
    line=soup.find_all("a",class_="title-credit-name")[0]
    for car in str(line):
        if car=="<" and cross==True:
            res=res[1:-1]
            return res
        elif cross==True:
            res+=car
        elif car==">" and cross==False:
            cross=True


def getCast(link):
    result=requests.get(link)
    soup = BeautifulSoup(result.text, 'html.parser')
    res=[]
    for line in soup.find_all("a",class_="title-credit-name"):
        cross=False
        name=""
        for car in str(line):
            if car=="<" and cross==True:
                name=name[1:-1]
                res.append(name)
                break
            elif cross==True:
                name+=car
            elif car==">" and cross==False:
                cross=True
    res.pop(0)
    res.pop(0)
    return res

print(getCast(urlfilm))
