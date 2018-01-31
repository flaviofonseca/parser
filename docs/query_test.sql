-- (1) Write MySQL query to find IPs that mode more than a certain number of requests for a given time period.
select ip, count(*) from parser.filelog 
where date_request between '2017-01-01.15.00:00:00' and '2017-01-01.15:59:59'
group by ip having count(*) >= 200;

--(2) Write MySQL query to find requests made by a given IP.
select * from parser.filelog where ip = '192.168.1.113';