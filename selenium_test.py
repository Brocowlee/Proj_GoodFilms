# from selenium import webdriver
# from selenium.webdriver.support.ui import WebDriverWait
# from selenium.webdriver.support import expected_conditions as EC
# from selenium.webdriver.common.keys import Keys
# from selenium.webdriver.common.by import By
# from selenium.webdriver.chrome.service import Service
# from webdriver_manager.chrome import ChromeDriverManager


# s = Service("C:\Program Files (x86)/chromedriver.exe")
# driver = webdriver.Chrome(service=s)
# driver.get("https://www.justwatch.com/fr/films")
# driver.implicitly_wait(10)

# driver.find_element_by_xpath('//*[@title="Accepter tout"]').click()

from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC



PATH = "C:\Program Files (x86)/chromedriver.exe"
driver = webdriver.Chrome(PATH)

driver.get("https://www.justwatch.com/fr/films")
driver.find_element_by_xpath('//*[@title="Accepter tout"]').click()

#<button role="button" data-testid="uc-accept-all-button" class="sc-gsDKAQ czzMyZ">Accepter tout</button>

# SCROLL_PAUSE_TIME = 0.5

# # Get scroll height
# last_height = driver.execute_script("return document.body.scrollHeight")

# while True:
#     # Scroll down to bottom
#     driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")

#     # Wait to load page
#     time.sleep(SCROLL_PAUSE_TIME)

#     # Calculate new scroll height and compare with last scroll height
#     new_height = driver.execute_script("return document.body.scrollHeight")
#     if new_height == last_height:
#         break
#     last_height = new_height