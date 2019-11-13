# System-Integration-Mini-Project-EIP

<h5>Made by: Hallur við Neyst, Hazem Saeid, Morshed Kayed</h5>

<h2>Brief summary of the application’s architecture, business and technical features</h2>

<p>
The project is made by two diffrent solutions (implemnt's) of sending and receiving messages:
  <li><strong>Producer and Consumer (with focus on topic)</strong></li>
  <img src="https://github.com/Mokayed/System-Integration-Mini-Project-EIP/blob/master/TOPIC.PNG"/>
  <li><strong>Requestor and Replier (with focus on RPC)</strong></li>
  <img src="https://github.com/Mokayed/System-Integration-Mini-Project-EIP/blob/master/rpc.PNG"/>
    
  <h2>Application’s architecture</h2>
  <img src="https://github.com/Mokayed/System-Integration-Mini-Project-EIP/blob/master/image.png"/>
  
  <ul>
  <li>
  data package is where the data files are processed (json/csv)
  </li>
  <li>
  entities is where the object for Food is
  </li>
  RPC: The package containing the implementation of RPC(Request-Reply Pattern) and other diffrent pattern which you can see by <a href="https://github.com/Mokayed/System-Integration-Mini-Project-EIP/blob/master/rpc.PNG">clicking her</a>. 
  
  <li>
  Topic: The package containing the implementation of Topics using the diffrent pattern that u can see by <a href="https://github.com/Mokayed/System-Integration-Mini-Project-EIP/blob/master/TOPIC.PNG">clicking her</a>.
  </li>
</ul>
  
  The topic part has to do with getting a list of food-categories and then filter the larger
  amount of data into a smaller chosen amount of data by using a routing_key.
  
  The RPC part follows the business process model and has to do with first picking a food category,
  then picking a food and then finally pay/not pay.
  
  this application processes a larger of amount of data in CSV and JSON format.
  
  <h2>Technical Features</h2>
  
  <ul>
  <li>message broker: rabbitMQ</li>
  <li>programming language: java 11</li>
  <li>dependecy manager: maven</li>
</ul>


  
image below shows our business process model, <a href="https://github.com/Mokayed/System-Integration-Mini-Project-EIP/blob/master/diagram.bpmn">click here for xml version</a>
</p>
<img src="https://github.com/Mokayed/System-Integration-Mini-Project-EIP/blob/master/bmpn.png"/>


<h5>installation instructions</h5>
<ul>
  <li>clone the project</li>
  <li>open it in your favorite java ide (we used IntelliJ)</li>
  <li>go into rpc package -> run Replier </li> 
  <li>then -> run Requester</li>
  <li>follow the instructions shown in console</li>
  <li>after following instructions, terminate the processes</li>
  <li>after following instructions, terminate the processes</li>
  <li>go into line 18 in TopicConsumer and edit the string to one of the following categories:</li>
  ----------------------------------------------------------
  <p>Beverages,Aquatic foods,Animal foods,Milk and milk products,Eggs,Confectioneries,Baking goods,Dishes,Snack foods,Baby foods,Unclassified,Fats and oils,Herbs and spices</p>
  ---------------------------------------------------------------
  <li>then go into topic package -> run TopicConsumer </li>
  <li>then run TopicProducer</li>
  <li>everything should all at once and you can see the results in the console window</li>
</ul>


