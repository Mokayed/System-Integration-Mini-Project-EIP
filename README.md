# System-Integration-Mini-Project-EIP

<h5>Made by: Hallur við Neyst, Hazem Saeid, Murched Kayed</h5>

<h2>Brief summary of the application’s <g-emoji class="g-emoji" alias="page_with_curl" fallback-src="https://github.githubassets.com/images/icons/emoji/unicode/1f4c3.png">📃</g-emoji></h2>

<p>
  We decided to create an application that exchanges big amount of food data which a client choose a food to order. The idea is that we have a producer that offer’s different kind of food to a consumer, then the consumer can pick one of the food he is interested in, after he could see the content of the food he want, then pay for it.
  
  <h4>BPM</h4>
  
  We started creating a business process model, which it would make it simpler to come the implementation phase, <a href="https://github.com/Mokayed/System-Integration-Mini-Project-EIP/blob/master/diagram.bpmn">click here for xml version</a>
</p>
<img src="https://github.com/Mokayed/System-Integration-Mini-Project-EIP/blob/master/bmpn.png"/>

  
  
  <h4> We decided to use those Technical Features to implement the solution </h4>

  <ul>
  <li>message broker: rabbitMQ</li>
  <li>programming language: java 11</li>
  <li>dependecy manager: maven</li>
</ul>

<h2>Implemention <g-emoji class="g-emoji" alias="computer" fallback-src="https://github.githubassets.com/images/icons/emoji/unicode/1f4bb.png">💻</g-emoji></h2>

<strong>The project is made by using two diffrent implementions of sending and receiving messages:</strong>

  <li><strong>The first implemention is a Producer and Consumer (with focus on topic)</strong></li>
  <img src="https://github.com/Mokayed/System-Integration-Mini-Project-EIP/blob/master/TOPIC.PNG"/>
  <li><strong>The secound implemention is Requestor and Replier (with focus on RPC)</strong></li>
  <img src="https://github.com/Mokayed/System-Integration-Mini-Project-EIP/blob/master/rpc.PNG"/>
    
  <h2>Application’s architecture & What we end with <g-emoji class="g-emoji" alias="interrobang" fallback-src="https://github.githubassets.com/images/icons/emoji/unicode/2049.png">⁉️</g-emoji></h2>
  
  
  <p align="right"><strong>Application’s architecture</strong></p>
  
  <img src="https://github.com/Mokayed/System-Integration-Mini-Project-EIP/blob/master/image.png" align="left"/>


  <ul>
  <li>
  The "data package" is where the data (json/csv) files are processed.
  </li>
  <li>
  the "entities package" contains our object class
  </li>
 The "RPC package" containing the implementation of RPC(Request-Reply Pattern) and other diffrent patterns which you can see by <a href="https://github.com/Mokayed/System-Integration-Mini-Project-EIP/blob/master/rpc.PNG">clicking her</a>. 
  
  <li>
 The "Topic package" containing the implementation of Topics using the diffrent patterns that u can see by <a href="https://github.com/Mokayed/System-Integration-Mini-Project-EIP/blob/master/TOPIC.PNG">clicking her</a>.
  </li>
</ul>

<strong>What we end with</strong>
  
  The topic part has to do with getting a list of food-categories and then filter the larger
  amount of data into a smaller chosen amount of data by using a routing_key.
  
  In RPC part we managed to implement our idea and it follows the business process model, we couldgo through those steps: first picking a food category, then picking a food, finally pay/not pay.
  

<h2>Installation instructions <g-emoji class="g-emoji" alias="gear" fallback-src="https://github.githubassets.com/images/icons/emoji/unicode/2699.png">⚙️</g-emoji></h2>
<ul>
  <li>clone the project</li>
  <li>open it in your favorite java ide (we used IntelliJ)</li>
  <li>go into RPC package -> run Replier </li> 
  <li>run Requester</li>
  <li>follow the instructions shown in console</li>
  <li>after following instructions, terminate the processes</li>
  <li>go into line 18 in TopicConsumer and edit the string to one of the following categories:</li>
  ----------------------------------------------------------
  <p>Beverages,Aquatic foods,Animal foods,Milk and milk products,Eggs,Confectioneries,Baking goods,Dishes,Snack foods,Baby foods,Unclassified,Fats and oils,Herbs and spices</p>
  ---------------------------------------------------------------
  <li>then go into topic package -> run TopicConsumer </li>
  <li>then run TopicProducer</li>
  <li>everything should all at once and you can see the results in the console window</li>
</ul>


