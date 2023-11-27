package main

import (
	"net/http"
	"github.com/gin-gonic/gin"
)

type User struct{
	Name string		`json:"name"`
	Age uint8 `json:"age"`
}

func ping(c *gin.Context){
	user := User{Name: "Jorge", Age: 98}
	c.JSON(http.StatusOK, user ) 
}

func main(){
	r := gin.Default()
	r.GET("/ping", ping)
	r.Run(":6969")
}
