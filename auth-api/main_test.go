package main

import (
    "net/http"
    "net/http/httptest"
    "strings"
    "testing"

    "github.com/labstack/echo"
    "github.com/stretchr/testify/assert"
)

func TestLoginEndpoint(t *testing.T) {
    e := echo.New()
    requestBody := `{"username":"test","password":"test"}`
    req := httptest.NewRequest(http.MethodPost, "/login", strings.NewReader(requestBody))
    req.Header.Set(echo.HeaderContentType, echo.MIMEApplicationJSON)
    rec := httptest.NewRecorder()
    c := e.NewContext(req, rec)

    // Suponiendo que Login es un handler que has definido en main.go
    if assert.NoError(t, Login(c)) {
        assert.Equal(t, http.StatusOK, rec.Code)
        // Aquí puedes agregar más aserciones basadas en la respuesta esperada
    }
}
