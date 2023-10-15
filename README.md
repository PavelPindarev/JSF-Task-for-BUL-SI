<h2>JSF-Task-for-BUL-SI</h2>
<h5>Task for menu with products. You can do CRUD operations only if you are logged in.</h5>
<h3>Local setup:</h3>
<p>after clone the app, configure Hibernate settings in HibernateUtil.
After that build the project 'mvn package', configure JBoss/WildFly local server to run the app.
In the configuration of server, set 'Artifact' in 'Deployment', where 'Artifact' is .war file.
</p>
<h2>Tech Stack:</h2>
<ul>
 <li>Java 17</li>
 <li>Jakarta Server Faces 3.0</li>
 <li>PrimeFaces 10</li>
 <li>Hibernate 6</li>
 <li>JBoss Weld 4</li>
 <li>MySQL</li>

</ul>

<h2>ER diagram:</h2>
<img src="./readme-img/er.png" alt="ER diagram">
<h3>Non Logged View</h3>
<img src="./readme-img/nonloged.png">
<h3>Logged View</h3>
<img src="./readme-img/logged.png">