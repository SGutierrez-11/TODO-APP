FROM node:8.17.0-alpine
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build

ENV PORT=8080
ENV AUTH_API_ADDRESS=http://auth-api:8000
ENV TODOS_API_ADDRESS=http://todos-api:8082

EXPOSE 8080

CMD ["npm", "start"]
