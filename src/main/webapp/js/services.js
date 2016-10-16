angular.module('app.services', [])

.factory('MyURL', [function(){
//	return {host: 'http://localhost:8787/tennisApp/'};
//	return {host: 'http://localhost:8787/ROOT/'};
	return {host: 'http://tennis-blackyellow.rhcloud.com/'};
				
}])

.service('BlankService', [function(){

}]);

