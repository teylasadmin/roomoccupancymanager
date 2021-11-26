# room occupancy manager
## How to run the service
In order to run the service, execute (run) class RoomManagerApplication
The default port for the service is 8082 

## How to test the service
There is just one endpoint of type POST. The easiest way to run it is via PostMan.

<B>URL</B> : http://localhost:8082/room-manager/profit-per-type

Body is of type <B>Text</B> and in the format:
```
Free Premium rooms: X
Free Economy rooms: Y
```
where ***X*** and ***Y*** and non-negative integers
