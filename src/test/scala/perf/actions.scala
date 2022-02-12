package perf

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object actions {

  private val headers_0 = Map(
    "Cache-Control" -> "max-age=0",
    "Proxy-Connection" -> "keep-alive"
  )

  private val headers_1 = Map("Proxy-Connection" -> "keep-alive")

  private val headers_3 = Map(
    "Cache-Control" -> "max-age=0",
    "Origin" -> "http://www.load-test.ru:1080",
    "Proxy-Connection" -> "keep-alive"
  )


  val getMainPage = http("getMainPage")
    .get("/webtours/")
    .headers(headers_0)
    .check(status is 200)
    .check(css("""frame[src='/cgi-bin/welcome.pl?signOff=true']""").exists)


  val getMainPage2 = http("getMainPage2")
    .get("/cgi-bin/welcome.pl?signOff=true")
    .headers(headers_1)
    .check(status is 200)
    .check(css("""frame[src='/WebTours/home.html']""").exists)


  val getMainPage3 = http("getMainPage3")
    .get("/cgi-bin/nav.pl?in=home")
    .headers(headers_1)
    .check(status is 200)
    .check(css("""input[name='userSession']""", """value""").find.saveAs("userSession"))


  val loginWebTours = http("loginWebTours")
    .post("/cgi-bin/login.pl")
    .headers(headers_3)
    .formParam("userSession", "#{userSession}")
    .formParam("username", "test666")
    .formParam("password", "test666")
    .formParam("login.x", "57")
    .formParam("login.y", "8")
    .formParam("JSFormSubmit", "off")
    .check(status is 200)


  val loginWebTours2 = http("loginWebTours2")
    .get("/cgi-bin/nav.pl?page=menu&in=home")
    .headers(headers_1)
    .check(status is 200)
    .check(regex("""Search Flights Button""").exists)


  val loginWebTours3 = http("loginWebTours3")
    .get("/cgi-bin/login.pl?intro=true")
    .headers(headers_1)
    .check(status is 200)
    .check(regex("""Using the menu to the left""").exists)


  val goToFlights = http("goToFlights")
    .get("/cgi-bin/welcome.pl?page=search")
    .headers(headers_1)
    .check(status is 200)
    .check(regex("""User has returned to the search page""").exists)

  val checkFlights = http("checkFlights")
    .get("/cgi-bin/reservations.pl?page=welcome")
    .header("userSession","#{userSession}")
    .check(status is 200)


  val addFlight = http("addFlight")
    .post("/cgi-bin/reservations.pl")
    .headers(headers_3)
    .formParam("advanceDiscount", "0")
    .formParam("depart", "#{depart}")
    .formParam("departDate", "11/04/2022")
    .formParam("arrive", "#{arrive}")
    .formParam("returnDate", "11/05/2022")
    .formParam("numPassengers", "1")
    .formParam("seatPref", "None")
    .formParam("seatType", "Coach")
    .formParam("findFlights.x", "47")
    .formParam("findFlights.y", "14")
    .formParam(".cgifields", "roundtrip")
    .formParam(".cgifields", "seatType")
    .formParam(".cgifields", "seatPref")
    .check(status is 200)
    .check(regex("""Flight departing from""").exists)
    .check(css("""input[checked='checked']""", """value""").find.saveAs("outboundFlight"))



  val addTime = http("addTime")
    .post("/cgi-bin/reservations.pl")
    .formParam("outboundFlight","#{outboundFlight}")
    .formParam("numPassengers","1")
    .formParam("advanceDiscount","0")
    .formParam("seatType","Coach")
    .formParam("seatPref","None")
    .formParam("reserveFlights.x", "70")
    .formParam("reserveFlights.y", "10")
    .check(status is 200)
    .check(regex("""Payment Details""").exists)


  val payTicket = http("payTicket")
    .post("/cgi-bin/reservations.pl")
    .headers(headers_3)
    .formParam("firstName","Alex")
    .formParam("lastName","Alexeev")
    .formParam("address1","")
    .formParam("address2","")
    .formParam("pass1","rr rr")
    .formParam("creditCard","")
    .formParam("expDate","")
    .formParam("oldCCOption","")
    .formParam("numPassengers","1")
    .formParam("seatType","Coach")
    .formParam("seatPref","None")
    .formParam("outboundFlight","#{outboundFlight}")
    .formParam("advanceDiscount","0")
    .formParam("returnFlight","")
    .formParam("JSFormSubmit","off")
    .formParam("buyFlights.x", "53")
    .formParam("buyFlights.y", "9")
    .formParam(".cgifields", "saveCC")
    .check(status is 200)
    .check(regex("""Thank you for booking through Web Tours""").exists)
}
