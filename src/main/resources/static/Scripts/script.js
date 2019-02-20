var isMapVisible = false;
var isTripFormVisible = false;
var isUserFormVisible = false;

var miasto;
var panstwo;

var identyfikator=new Array();
var nazwaMiasta=new Array();
var nazwaPanstwa=new Array();

// === miasta i kraje ===
var url = 'http://localhost:1234/city/showAllCity';

// Replace ./data.json with your JSON feed
fetch(url).then(response => {
  return response.json();
}).then(data => {
  var json = JSON.stringify(data);
json=JSON.parse(json);
var dlugosc=json.length;
dlugosc=JSON.stringify(dlugosc);


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
}).catch(err => {
  document.getElementById('display-trips').innerHTML = "Coś poszło nie tak!";
});

// === uzytkownicy ===

var userId=new Array();
var userName=new Array();
var userLast=new Array();
var userMail=new Array();

var url = 'http://localhost:1234/getAllUsers';

// Replace ./data.json with your JSON feed
fetch(url).then(response => {
  return response.json();
}).then(data => {
  var json = JSON.stringify(data);
json=JSON.parse(json);
var dlugosc=json.length;
dlugosc=JSON.stringify(dlugosc);

var userId=new Array();
var userName=new Array();
var userLast=new Array();
var userMail=new Array();



//for do tworzenia tablic
for(var i=0;i<dlugosc;i++){
  var tmpUserId=json[i].userId;
  var tmpName=json[i].userName;
  var tmpLast=json[i].userLastName;
  var tmpMail=json[i].userEmail;

  tmpUserId=JSON.stringify(tmpUserId);
  tmpName=JSON.stringify(tmpName);
  tmpLast=JSON.stringify(tmpLast);
  tmpMail=JSON.stringify(tmpMail);

  userId.push(tmpId);
  userName.push(tmpName);
  userLast.push(tmpLast);
  userMail.push(tmpMail);

}

}).catch(err => {
  document.getElementById('display-users').innerHTML = 'Coś poszło nie tak!';
});

$(document).ready(function() {

//efekty na textboxach
  $('.input-form').focus(function() {
    textBoxTransitions( $(this).siblings('p'));
  });

  $('.text-box-effect').click(function() {
    textBoxTransitions(this);
    $(this).siblings('.input-form').focus();
  });

  $('.input-form').blur(function() {
    if($(this).val().length == 0)
    {
      $(this).siblings('p:first-letter').css({
        'text-transform' : 'uppercase'
      });
      $(this).siblings('p').css({
        'transform' : 'translate(1em, 0.75em)',
        'font-size': '16px',
        'background-color': 'rgba(0,0,0,0)',
        'text-transform' : 'lowercase'
      });
    }
  });
});


function textBoxTransitions(element) {
  $(element).css({
    'transform': 'translate(1em, -0.5em)',
    'font-size': '12px',
    'background-color': 'white',
    'text-transform' : 'uppercase'
  });
}


//obsługa paska nawigacji
function openTab(e, tabName) {
  var i;
  var x = $('.tab');
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";
  }

  tabs = $('.nav-bar-item');
  for(i=0; i< x.length; i++) {
    tabs[i].className = tabs[i].className.replace(' active', '');
  }

  document.getElementById(tabName).style.display = "block";
  e.currentTarget.className += ' active';

  if(tabName == 'base'){

    if(!isMapVisible) {
      map = new OpenLayers.Map("mapdiv");
      map.addLayer(new OpenLayers.Layer.OSM());
      var lonLat = new OpenLayers.LonLat(-0.1279688, 51.5077286)
          .transform(
              new OpenLayers.Projection("EPSG:4326"), // transform from WGS 1984
              map.getProjectionObject() // to Spherical Mercator Projection
          );
      var zoom = 10;
      var markers = new OpenLayers.Layer.Markers("Markers");
      map.addLayer(markers);
      markers.addMarker(new OpenLayers.Marker(lonLat));
      map.setCenter(lonLat, zoom);

      isMapVisible = true;
    }

    if(!isTripFormVisible){

      var string = "";

      for(var i=0;i<nazwaMiasta.length;i++){
        var s = '<tr><td><a href="https://pl.wikipedia.org/wiki/' + nazwaMiasta[i] + '">' + nazwaMiasta[i]+'</a></td><td><a href="https://pl.wikipedia.org/wiki/' + nazwaPanstwa[i] + '">' + nazwaPanstwa[i]+ "</a></td></tr>";
        string += s;
      }

      string = "<table style='width:100%'><tr><th>Miasto</th><th>Państwo</th></tr>" + string + "</table>";
      document.getElementById('display-trips').innerHTML = string ;

      isTripTabVisible = true;
    }
  }
}



//wybór formularza
function openForm(e, formName) {
  var i;
  var x = $('.form-container');
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";
  }

  forms = $('.form-button');
  for(i=0; i< x.length; i++) {
    forms[i].className = forms[i].className.replace(' button-active', '');
  }

  if(formName == 'trip') {
    document.getElementById('bc-item').innerHTML = "Cele podróży";
  }

  if(formName == 'user') {
    document.getElementById('bc-item').innerHTML = "Użytkownicy";

    if(!isUserFormVisible){

      var string = "";

      for(var i=0;i<userName.length;i++){
        var s = "<tr><td>" + userName[i]+"</td><td>" + userLast[i]+ "</td><td>" + userMail[i]+ "</td></tr>";
        string += s;
      }

      string = "<table style='width:100%'><tr><th>Imię</th><th>Nazwisko</th> <th>E-mail</th></tr>" + string + "</table>";
      document.getElementById('display-users').innerHTML = string ;

      isUserFormVisible = true;
    }

  }


  document.getElementById(formName).style.display = "block";
  e.currentTarget.className += ' button-active';
}



/*
//listy wyboru
function openList(e, listName) {

  var i;
  var x = $('.select-list');
  for (i = 0; i < x.length; i++) {
    $(x[i]).slideUp('fast');
  }

  $('#'+listName).slideToggle('fast');
  e.currentTarget.className += ' active';
*/
