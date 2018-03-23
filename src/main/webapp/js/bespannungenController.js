/**
 * 
 */
angular.module('app.controllers')
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
	$http.get(MyURL.hostNeu+'saiten/alle')
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
