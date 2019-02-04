var url = 'http://localhost:1234/city/showAllCity';

// Replace ./data.json with your JSON feed
fetch(url).then(response => {
  return response.json();
}).then(data => {
  var json = JSON.stringify(data);
  json=JSON.parse(json);
  var dlugosc=json.length;
  dlugosc=JSON.stringify(dlugosc);

    var identyfikator=new Array();
    var nazwaMiasta=new Array();
    var nazwaPanstwa=new Array();
    //for do tworzenia tablic
    for(var i=0;i<dlugosc;i++){
        var tmpId=json[i].cityId;
        var tmpCity=json[i].cityName;
        var tmpCountry=json[i].countryName;

        tmpId=JSON.stringify(tmpId);
        tmpCity=JSON.stringify(tmpCity);
        tmpCountry=JSON.stringify(tmpCountry);

        identyfikator.push(tmpId);
        nazwaMiasta.push(tmpCity);
        nazwaPanstwa.push(tmpCountry);
    }
    document.getElementById('display').innerHTML = (nazwaPanstwa[0]+" "+nazwaMiasta[0]+" "+identyfikator[0]);
      console.log(dlugosc);
}).catch(err => {
  // Do something for an error here
});
