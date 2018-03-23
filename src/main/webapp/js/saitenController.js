/**
 * 
 */
angular.module('app.controllers')
.controller('saitenCtrl', function($scope, $http, MyURL) {
	$scope.$on('$ionicView.enter', function() {		
	$scope.saiten = null;
			$http.get(MyURL.hostNeu+'saiten/alle')
					.success(function(data) {
						$scope.saiten = data;
					}).error(function(data, status, headers, config) {
					});
	});
})

.controller('saitenDetailCtrl', function($scope, $stateParams, $http, $state, MyURL) {
	$scope.saite = null;

	var parameter = $stateParams.saitenId;

	$http.get(MyURL.hostNeu+'saiten/id/'+parameter)
	.success(function(data) {
		$scope.saite = data;
	}).error(function(data, status, headers, config) {
	});
	
	$scope.save = function(neu)
	{
		$http({
			url : MyURL.hostNeu+'saiten',
			method : 'PUT',
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