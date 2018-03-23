/**
 * alle Controller zum Thema Kunde.
 */
angular.module('app.controllers')
  
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