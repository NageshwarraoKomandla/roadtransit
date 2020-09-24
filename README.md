########################################################################################
# Spring Boot application to  check connectivity between two given cities exist or not #
######################################################################################## 

Swgger URL for application : http://localhost:8093/roadtransit/swagger-ui.html#/roadtransit-controller/getDataUsingGET

Service endpoint: http://localhost:8093/roadtransit/connected?origin=Newark&destination=Boston

Functionality:
Service  will response  with ‘yes’ if city1 is connected to city2, ’no’ if city1 is not connected to city2.


Data Setup /Configuration data:  
 Boston, New York
 Philadelphia, Newark
 Newark, Boston
 Trenton, Albany


 return yes http://localhost:8093/roadtransit/connected?origin=Boston&destination=Philadelphia 
 return no  http://localhost:8093/roadtransit/connected?origin=Philadelphia&destination=Albany  
