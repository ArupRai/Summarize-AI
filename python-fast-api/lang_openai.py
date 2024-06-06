from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from openai import OpenAI
import uvicorn
import os
from dotenv import load_dotenv
from fastapi.responses import PlainTextResponse  # Import PlainTextResponse

load_dotenv()  # Load environment variables from .env file
app = FastAPI()

# Retrieve OpenAI API key from environment variable
api_key = os.getenv("OPENAI_API_KEY")
if not api_key:
    raise ValueError("OPENAI_API_KEY environment variable not set")
# Initialize OpenAI client
client = OpenAI(
    api_key=api_key,
    base_url="https://api.llama-api.com"
)

class SummaryRequest(BaseModel):
    webapp_link: str

@app.post("/summarize", response_class=PlainTextResponse)
def summarize(request: SummaryRequest):
    try:
        response = client.chat.completions.create(
            model="llama-13b-chat",
            messages=[
                {"role": "system", "content": "Assistant is a large language model trained by OpenAI."},
                {"role": "user", "content": f"Summarize '{request.webapp_link}'"}
            ],
            max_tokens=500,
            temperature=0.5,
            stream=False,
            functions=None
        )
        summary = response.choices[0].message.content
        return summary
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8000)


