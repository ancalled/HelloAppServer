$(document).ready(function() {

    var currCat = '';
    var currPlace = '';

    function init() {
        retrieveDiscounts();

//        retrieveCategories();
//
//        $('#menu_cats').click(function() {
//            retrieveCategories();
//        });
//
//        $('#menu_places').click(function() {
//            if (currCat) {
//                retrievePlaces(currCat);
//            }
//        });
//
//        $('#menu_events').click(function() {
//            if (currPlace) {
//                retrieveEvents(currCat);
//            }
//        });
    }


    function retrieveDiscounts() {
        $.getJSON('/helloapp/discounts', function(data) {
            showDicsounts(data)
        });
    }


    function retrieveCategories() {
        $.getJSON('/helloapp/cats', function(data) {
            showCategories(data)
        });
    }

    function retrievePlaces(cat) {
        $.getJSON('/helloapp/cat/' + cat, function(data) {
            showPlaces(data);
            currCat = cat;
        });
    }

    function retrieveEvents(place) {
        $.getJSON('/helloapp/place/' + place, function(data) {
            showEvents(data)
            currPlace = place;
        });
    }


    function showDicsounts(data) {
        var items = [];

        items.push('<li class="nav-header">Сикдки</li>');

        $.each(data, function() {
            items.push('<li id="' + this.name + '"><a href="#' + this.name + '">' + this.title + '</a></li>');
        });

        $('#widgetBody').empty();

        $('<ul/>', {
            'class': 'nav nav-list',
            html: items.join('')
        }).appendTo('#widgetBody');

        $.each(data, function() {
            $('#' + this.name + ' a').click(function(e) {
                e.preventDefault();
                var nm = $(this).parent().attr('id');
                retrievePlaces(nm)
            });
        });

        $('#widgetMenu li').removeClass("active");
        $('#menu_cats').addClass("active");
    }


    function showCategories(data) {
        var items = [];

        items.push('<li class="nav-header">Категории</li>');

        $.each(data, function() {
            items.push('<li id="' + this.name + '"><a href="#' + this.name + '">' + this.title + '</a></li>');
        });

        $('#widgetBody').empty();

        $('<ul/>', {
            'class': 'nav nav-list',
            html: items.join('')
        }).appendTo('#widgetBody');

        $.each(data, function() {
            $('#' + this.name + ' a').click(function(e) {
                e.preventDefault();
                var nm = $(this).parent().attr('id');
                retrievePlaces(nm)
            });
        });

        $('#widgetMenu li').removeClass("active");
        $('#menu_cats').addClass("active");
    }


    function showPlaces(data) {
        var items = [];

        items.push('<li class="nav-header">Места</li>');

        $.each(data, function() {
            items.push('<li id="' + this.name + '"><a href="#' + this.name + '">' + this.title + '</a></li>');
        });

        $('#widgetBody').empty();

        $('<ul/>', {
            'class': 'nav nav-list',
            html: items.join('')
        }).appendTo('#widgetBody');

        $.each(data, function() {
            $('#' + this.name + ' a').click(function(e) {
                e.preventDefault();
                var nm = $(this).parent().attr('id');
                retrieveEvents(nm);
            });
        });

        $('#widgetMenu li').removeClass("active");
        $('#menu_places').addClass("active");

    }


    function showEvents(data) {
        var items = [];

        items.push('<li class="nav-header">События</li>');

        $.each(data, function() {
            items.push('<li id="' + this.name + '">' + this.title + '<span>' + this.eventDate +  '</span></li>');
        });

        $('#widgetBody').empty();

        $('<ul/>', {
            'class': 'nav nav-list',
            html: items.join('')
        }).appendTo('#widgetBody');

        $('#widgetMenu li').removeClass("active");
        $('#menu_events').addClass("active");
    }


//    $(window).bind( 'hashchange', function(e) {
//        hash = location.hash;
//        console.log('location changed: ' + hash)
//
//    });



    init();

});
