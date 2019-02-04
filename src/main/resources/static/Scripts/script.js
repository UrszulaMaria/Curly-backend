
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

  if(formName == 'trip-filter') {
    document.getElementById('bc-item').innerHTML = "Użytkownicy";
  }

  document.getElementById(formName).style.display = "block";
  e.currentTarget.className += ' button-active';
}

//listy wyboru
function openList(e, listName) {

  var i;
  var x = $('.select-list');
  for (i = 0; i < x.length; i++) {
    $(x[i]).slideUp('fast');
  }

  $('#'+listName).slideToggle('fast');
  e.currentTarget.className += ' active';
}
