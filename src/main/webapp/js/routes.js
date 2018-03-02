angular.module('app.routes', [])

.config(function($stateProvider, $urlRouterProvider) {

//	$ionicConfigProvider.views.maxCache(0);
  // Ionic uses AngularUI Router which uses the concept of states
  // Learn more here: https://github.com/angular-ui/ui-router
  // Set up the various states which the app can be in.
  // Each state's controller can be found in controllers.js
  $stateProvider
    
  

      .state('cMSuperDuperApp.kunden', {
    url: '/page2',
    views: {
      'tab1': {
        templateUrl: 'templates/kunden.html',
        controller: 'kundenCtrl'
      }
    }
  })
  
  .state('cMSuperDuperApp.kundeDetail', {
    url: '/kunde',
    views: {
      'tab1': {
        templateUrl: 'templates/kundenDetail.html',
        controller: 'kundenDetailCtrl'
      }
    }
  })
  
  .state('cMSuperDuperApp.kundeBearbeiten', {
    url: '/kunde/:kundenNr',
    views: {
      'tab1': {
        templateUrl: 'templates/kundenDetail.html',
        controller: 'kundenDetailCtrl'
      }
    }
  })

  .state('cMSuperDuperApp.schlaegerBearbeiten', {
    url: '/schlaeger/:schlaegerId',
    views: {
      'tab1': {
        templateUrl: 'templates/schlaegerBearbeiten.html',
        controller: 'schlaegerBearbeiten'
      }
    }
  })
  
  .state('cMSuperDuperApp.schlaeger', {
    url: '/page3',
    views: {
      'tab2': {
        templateUrl: 'templates/schlGer.html',
        controller: 'schlaegerCtrl'
      }
    }
  })
  
  .state('cMSuperDuperApp.schlaegerDetail', {
    url: '/schlaegerDetail/:modellId',
    views: {
      'tab2': {
        templateUrl: 'templates/schlaegerDetail.html',
        controller: 'schlaegerDetailCtrl'
      }
    }
  })

  .state('cMSuperDuperApp.saiten', {
    url: '/page4',
    cache : false,
    views: {
      'tab3': {
        templateUrl: 'templates/saiten.html',
        controller: 'saitenCtrl'
      }
    }
  })
  
  .state('cMSuperDuperApp.saiteDetail', {
    url: '/saiteDetail/:saitenId',
    views: {
      'tab3': {
        templateUrl: 'templates/saiteDetail.html',
        controller: 'saitenDetailCtrl'
      }
    }
  })

  .state('cMSuperDuperApp', {
    url: '/page1',
    templateUrl: 'templates/cMSuperDuperApp.html',
    abstract:true
  })

  .state('cMSuperDuperApp.schlaegeruebersicht', {
    url: '/page5/:kundenId',
    views: {
      'tab1': {
        templateUrl: 'templates/schlaegeruebersicht.html',
        controller: 'schlaegeruebersichtCtrl'
      }
    }
  })

  .state('cMSuperDuperApp.bespannungen', {
    url: '/page6/:schlaegerId',
    views: {
      'tab1': {
        templateUrl: 'templates/bespannungen.html',
        controller: 'bespannungenCtrl'
      }
    }
  })

  .state('cMSuperDuperApp.bespBearbeiten', {
    url: '/page8/:bespId/:schlaegerId',
    views: {
      'tab1': {
        templateUrl: 'templates/bearbeiten.html',
        controller: 'bespBearbeitenCtrl'
      }
    }
  })
  
  .state('cMSuperDuperApp.neuerSchlaeger', {
    url: '/neuerSchlaeger/:kundenNr',
    views: {
      'tab1': {
        templateUrl: 'templates/neuerSchlaeger.html',
        controller: 'neuerSchlaegerCtrl'
      }
    }
  })

$urlRouterProvider.otherwise('/page1/page2')

  

});