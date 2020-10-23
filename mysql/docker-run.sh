
docker run -d -v $(pwd)/data:/var/lib/mysql --rm --name mysql-spring -p 3306:3306 db-mysql