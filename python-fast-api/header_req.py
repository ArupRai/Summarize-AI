import requests
webapp_link_user_input = input("Enter a web app link to summarize it: ")
url = "http://localhost:8000/summarize"
payload = {"webapp_link": webapp_link_user_input}
headers = {"Content-Type": "application/json"}

response = requests.post(url, json=payload, headers=headers)

print("\n"+response.text+"\n")
