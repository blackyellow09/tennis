angular.module('app.services', [])

.factory('MyURL', [function(){
//	return {host: 'http://localhost:8787/tennisApp/'};
//	return {host: 'http://localhost:8787/ROOT/'};
//	return {host: 'http://localhost:8787/tennis/',
//		hostNeu: 'http://localhost:8787/tennis/tennis/'};
	return {host: 'http://tennis1-tennis-app.a3c1.starter-us-west-1.openshiftapps.com/', 
		hostNeu: 'http://tennis1-tennis-app.a3c1.starter-us-west-1.openshiftapps.com/tennis/'};
				
}])

.service('BlankService', [function(){

}]);

