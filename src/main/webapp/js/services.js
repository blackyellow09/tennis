angular.module('app.services', [])

.factory('MyURL', [function(){
//	return {host: 'http://localhost:8787/tennisApp/'};
//	return {host: 'http://localhost:8787/ROOT/'};
	return {host: 'http://tennis1-tennis-app.a3c1.starter-us-west-1.openshiftapps.com/'};
				
}])

.service('BlankService', [function(){

}]);

