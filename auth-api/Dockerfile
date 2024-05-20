FROM golang:1.18.2
WORKDIR /go/src/app
COPY . .
RUN export GO111MODULE=on
RUN go mod init github.com/bortizf/microservice-app-example/tree/master/auth-api
RUN go mod tidy
RUN go build -o main .


EXPOSE 8000


ENV AUTH_API_PORT=8000
ENV USERS_API_ADDRESS=http://users-api:8083
ENV JWT_SECRET=PRFT

CMD ["./main"]
