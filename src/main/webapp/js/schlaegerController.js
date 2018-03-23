/**
 * 
 */
angular.module('app.controllers')
.controller('schlaegerBearbeiten', function($scope, $stateParams, $http, $state, MyURL) {
	var parameter = $stateParams.schlaegerId;
	var modellnr = $stateParams.modellNr;

	$scope.$on('$ionicView.enter', function() {		
		$scope.modelle = null;
		$http({
			url : MyURL.hostNeu+'schlaegerBearbeiten',
			method : 'GET',
			params : {
				id : parameter,
			},
			headers : {
				'Content-Type' : 'application/json;charset=utf-8'
			}
		}).success(function(data) {
			$scope.modelle = data.schlaeger;
			$scope.aktiv = data.aktiv;
		}).error(function(data, status, headers, config) {
			$scope.msg = "Fehler beim Laden";

		});
	});
	$scope.schlaeger = parseInt(modellnr);

	$scope.save = function(schlaeger, aktiv)
	{
		$http({
			url : MyURL.hostNeu+'schlaegerBearbeiten',
			method : 'PUT',
			params : {
				id : parameter,
				modell : JSON.stringify(schlaeger), 
				aktiv : JSON.stringify($scope.aktiv)
			},
			headers : {
				'Content-Type' : 'application/json;charset=utf-8'
			}
		}).success(function(data) {
				$state.transitionTo('cMSuperDuperApp.schlaegeruebersicht', {kundenId: data}, {
			    reload: true,
			    inherit: true,
			    notify: true
				});
		}).error(function(data, status, headers, config) {
			$scope.msg = "Fehler beim Speichern";

		});
	}
})
   
.controller('schlaegerCtrl', function($scope, $http, MyURL) {
	$scope.$on('$ionicView.enter', function() {		
		$scope.schlaeger = null;
				$http.get(MyURL.host+'schlaegerServlet')
						.success(function(data) {
							$scope.schlaeger = data;
						}).error(function(data, status, headers, config) {
						});
		});
})

.controller('schlaegerDetailCtrl', function($scope, $stateParams, $http, $state, MyURL) {
	$scope.modell = null;

	$scope.marken = null;
	$http.get(MyURL.host+'markenServlet')
			.success(function(data) {
				$scope.marken = data;
			}).error(function(data, status, headers, config) {
			});
	
	var parameter = $stateParams.modellId;

	$http({
		url : MyURL.host+'schlaegerServlet',
		method : 'POST',
		params : {
			id : parameter
		},
		headers : {
			'Content-Type' : 'application/json;charset=utf-8'
		}
	}).success(function(data) {
		$scope.modell = data;
	}).error(function(data, status, headers, config) {

	});
	
	
	$scope.save = function(neu)
	{
		console.log(neu);
		$http({
			url : MyURL.host+'schlaegerServlet',
			method : 'POST',
			params : {
				id : parameter,
				modell : JSON.stringify(neu)
			},
			headers : {
				'Content-Type' : 'application/json;charset=utf-8'
			}
		}).success(function(data) {
				$state.transitionTo('cMSuperDuperApp.schlaeger', $state.$current.params, {
			    reload: true,
			    inherit: true,
			    notify: true
				});
		}).error(function(data, status, headers, config) {
			$scope.msg = "Fehler";

		});
	}
})
.controller('schlaegeruebersichtCtrl', function($scope, $stateParams, $http, MyURL) {
	$scope.item = null;

	var parameter = $stateParams.kundenId;

	$http({
		url : MyURL.host+'kundenServlet',
		method : 'POST',
		params : {
			id : parameter
		},
		headers : {
			'Content-Type' : 'application/json;charset=utf-8'
		}
	}).success(function(data) {
		$scope.item = data;
	}).error(function(data, status, headers, config) {

	});
})
.controller('neuerSchlaegerCtrl', function($scope, $stateParams, $http, MyURL, $state) {

	var parameter = $stateParams.kundenNr;
	$http({
		url : MyURL.host+'schlaegerZuordnungServlet',
		method : 'POST',
		params : {
			id : parameter
		},
		headers : {
			'Content-Type' : 'application/json;charset=utf-8'
		}
	}).success(function(data) {
		$scope.kunde = data.kunde;
		$scope.schlaegermodelle = data.schlaeger;
	}).error(function(data, status, headers, config) {

	});
	
	$scope.save = function(kunde, schlaeger){
		$http({
	 		url : MyURL.host+'neuerSchlaegerServlet',
	 		method : 'POST',
	 		params : {
	 			kundenId : kunde.kundennummer,
	 			schlaegerId: schlaeger.modellNr,
	 		},
	 		headers : {
	 			'Content-Type' : 'application/json;charset=utf-8'
	 		}
	 	}).success(function(data) {
 			$state.go('cMSuperDuperApp.schlaegeruebersicht', {kundenId: kunde.kundennummer});
	 	}).error(function(data, status, headers, config) {
	 	});
	}
	
})