var url = 'http://localhost:1234/getAllUsers';

// Replace ./data.json with your JSON feed
fetch(url).then(response => {
  return response.json();
}).then(data => {
  var json = JSON.stringify(data);
  json=JSON.parse(json);
  var dlugosc=json.length;
  dlugosc=JSON.stringify(dlugosc);

    var identyfikator=new Array();
    var userName=new Array();
    var userLast=new Array();
    var userMail=new Array();



    //for do tworzenia tablic
    for(var i=0;i<dlugosc;i++){
        var tmpId=json[i].userId;
        var tmpName=json[i].userName;
        var tmpLast=json[i].userLastName;
        var tmpMail=json[i].userEmail;

        tmpId=JSON.stringify(tmpId);
        tmpName=JSON.stringify(tmpName);
        tmpLast=JSON.stringify(tmpLast);
        tmpMail=JSON.stringify(tmpMail);

        identyfikator.push(tmpId);
        userName.push(tmpName);
        userLast.push(tmpLast);
        userMail.push(tmpMail);

    }
    document.getElementById('display').innerHTML = (identyfikator[2]+"<br/>"+userName[2]+""+userLast[2]+" "+userMail[2]);
      console.log(dlugosc);
}).catch(err => {
  // Do something for an error here
});
