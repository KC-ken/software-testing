import webbrowser
from xml.dom.xmlbuilder import DOMEntityResolver
from selenium import webdriver as wd
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from selenium.webdriver.support.relative_locator import locate_with
from selenium.webdriver.common.keys import Keys

# launch a chrome browser and navigating to nycu webside
driver = wd.Chrome(service=Service(ChromeDriverManager().install()))
driver.get("https://www.nycu.edu.tw/")

# maximizing the window
driver.maximize_window()

# find the link text 消息, and click it
driver.implicitly_wait(10)
news = driver.find_element(By.LINK_TEXT, "消息")
news.click()

# find the first link text below to the span 科學新知
driver.implicitly_wait(10)
relative_locator = driver.find_element(By.XPATH, "//span[.='科學新知']")
first_news = driver.find_element(locate_with(By.TAG_NAME, "a").below(relative_locator))
first_news.click()

# get the all content and print out
driver.implicitly_wait(10)
content = driver.find_element(By.ID, "content").text
print(content)

# create and switch to a new tab
driver.switch_to.new_window("tab")

# navigate to google
driver.get("https://www.google.com")

# search my student ID on Google
driver.find_element(By.NAME, "q").send_keys("309511075" + Keys.ENTER)

# print the title of second result
search_result = driver.find_elements(By.CSS_SELECTOR, "#res h3")
print("\n", search_result[1].text)

# quit the browser
driver.quit()