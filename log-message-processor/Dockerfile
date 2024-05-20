FROM python:3.6-slim
WORKDIR /app
COPY requirements.txt .
RUN apt-get update && apt-get install -y gcc
RUN pip3 install --upgrade pip
RUN pip3 install --no-cache-dir -r requirements.txt
COPY . .

ENV REDIS_HOST=redis-service
ENV REDIS_PORT=6379
ENV REDIS_CHANNEL=log_channel
CMD ["python3", "main.py"]
