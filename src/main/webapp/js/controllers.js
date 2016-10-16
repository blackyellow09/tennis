angular.module('app.controllers', [])
  
.controller('kundenCtrl', function($scope, $http, MyURL) {
	$scope.$on('$ionicView.enter', function() {		
		$scope.kunden = null;
		$http.get(MyURL.host+'kundenServlet')
				.success(function(data) {
					$scope.kunden = data;
				}).error(function(data, status, headers, config) {
				});
		});
})

.controller('kundenDetailCtrl', function($scope, $stateParams, $http, $state, MyURL) {
	$scope.kunde = null;

	var parameter = $stateParams.kundenNr;

	$http({
		url : MyURL.host+'kundenServlet',
		method : 'POST',
		params : {
			id : parameter,
			edit: 0
		},
		headers : {
			'Content-Type' : 'application/json;charset=utf-8'
		}
	}).success(function(data) {
		$scope.kunde = data;
	}).error(function(data, status, headers, config) {

	});
	
	
	$scope.save = function(neu)
	{
		$http({
			url : MyURL.host+'kundenServlet',
			method : 'POST',
			params : {
				id : parameter,
				kunde : JSON.stringify(neu)
			},
			headers : {
				'Content-Type' : 'application/json;charset=utf-8'
			}
		}).success(function(data) {
				$state.transitionTo('cMSuperDuperApp.kunden', {
			    reload: true,
			    inherit: true,
			    notify: true
				});
		}).error(function(data, status, headers, config) {
			$scope.msg = "Fehler";

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
   
.controller('saitenCtrl', function($scope, $http, MyURL) {
	$scope.$on('$ionicView.enter', function() {		
	$scope.saiten = null;
			$http.get(MyURL.host+'saitenServlet')
					.success(function(data) {
						$scope.saiten = data;
					}).error(function(data, status, headers, config) {
					});
	});
})

.controller('saitenDetailCtrl', function($scope, $stateParams, $http, $state, MyURL) {
	$scope.saite = null;

	var parameter = $stateParams.saitenId;

	$http({
		url : MyURL.host+'saitenServlet',
		method : 'POST',
		params : {
			id : parameter
		},
		headers : {
			'Content-Type' : 'application/json;charset=utf-8'
		}
	}).success(function(data) {
		$scope.saite = data;
	}).error(function(data, status, headers, config) {

	});
	
	
	$scope.save = function(neu)
	{
		$http({
			url : MyURL.host+'saitenServlet',
			method : 'POST',
			params : {
				id : parameter,
				saite : JSON.stringify(neu)
			},
			headers : {
				'Content-Type' : 'application/json;charset=utf-8'
			}
		}).success(function(data) {
				$state.transitionTo('cMSuperDuperApp.saiten', $state.$current.params, {
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
   
.controller('bespannungenCtrl', function($scope, $stateParams, $http, MyURL) {
	$scope.$on('$ionicView.enter', function() {	
		$scope.bespannungen = null;
	
		var parameter = $stateParams.schlaegerId;
		$scope.schlaegerId = $stateParams.schlaegerId;
	
		$http({
			url : MyURL.host+'bespannungServlet',
			method : 'POST',
			params : {
				id : parameter
			},
			headers : {
				'Content-Type' : 'application/json;charset=utf-8'
			}
		}).success(function(data) {
			$scope.bespannungen = data;
			angular.forEach($scope.bespannungen.bespannungen, function(value, key){
				value.datum = new Date(value.datum);
			});
		}).error(function(data, status, headers, config) {
	
		});
	});
})
   
.controller('bespBearbeitenCtrl', function($scope, $stateParams, $http, $state, $filter, MyURL) {
	$scope.bespannung = null;
	$scope.schlaeger = null;
	$scope.kunde = null

	$scope.saiten = null;
	$http.get(MyURL.host+'saitenServlet')
			.success(function(data) {
				$scope.saiten = data;
			}).error(function(data, status, headers, config) {
			});
	
	var parameter = $stateParams.bespId;
	$scope.bespId = $stateParams.bespId;

	if($scope.bespId == "")
		{
		$scope.schlaegerId = $stateParams.schlaegerId;
		$http({
			url : MyURL.host+'bespannungBearbeitenServlet',
			method : 'POST',
			params : {
				schlaegerId : $scope.schlaegerId
			},
			headers : {
				'Content-Type' : 'application/json;charset=utf-8'
			}
		}).success(function(data) {
			$scope.schlaeger = data.schlaeger;
			$scope.kunde = data.kunde;
		}).error(function(data, status, headers, config) {
			});
		}
	else
		{
			$http({
				url : MyURL.host+'bespannungBearbeitenServlet',
				method : 'POST',
				params : {
					id : parameter
				},
				headers : {
					'Content-Type' : 'application/json;charset=utf-8'
				}
			}).success(function(data) {
				$scope.bespannung = data.bespannung;
				$scope.bespannung.datum = new Date($scope.bespannung.datum);
				$scope.schlaeger = data.schlaeger;
				$scope.kunde = data.kunde;
			}).error(function(data, status, headers, config) {
				});
			
		}
	var schlaegerId = $scope.schlaegerId;
	
	$scope.save = function(neu)
	{
		$http({
			url : MyURL.host+'bespannungBearbeitenServlet',
			method : 'POST',
			params : {
				id : parameter,
				bespannung : JSON.stringify(neu),
				schlaegerId : schlaegerId
			},
			headers : {
				'Content-Type' : 'application/json;charset=utf-8'
			}
		}).success(function(data) {
				$state.transitionTo('cMSuperDuperApp.bespannungen', {schlaegerId: $scope.schlaeger.schlaegerId}, {
			    reload: true,
			    inherit: true,
			    notify: true
				});
		}).error(function(data, status, headers, config) {
			$scope.msg = "Fehler";

		});
	}
})
 