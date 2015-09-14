
/**
 * HOMER - Responsive Admin Theme
 * version 1.7
 *
 */
(function () {
    angular.module('homer', [
        'ui.router',                // Angular flexible routing
        'ngSanitize',               // Angular-sanitize
        'ui.bootstrap',             // AngularJS native directives for Bootstrap
        'angular-flot',             // Flot chart
        'angles',                   // Chart.js
        'angular-peity',            // Peity (small) charts
        'cgNotify',                 // Angular notify
        'angles',                   // Angular ChartJS
        'ngAnimate',                // Angular animations
        'ui.map',                   // Ui Map for Google maps
        'ui.calendar',              // UI Calendar
        'summernote',               // Summernote plugin
        'ngGrid',                   // Angular ng Grid
        'ui.tree',                  // Angular ui Tree
        'bm.bsTour',                // Angular bootstrap tour
        'datatables',               // Angular datatables plugin
        'xeditable',                // Angular-xeditable
        'ui.select',                // AngularJS ui-select
        'ui.sortable',              // AngularJS ui-sortable
        'ui.footable'               // FooTable
    ]).config(function($httpProvider) {

		$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
	
	}).controller('navigation',
			
			function($scope, $http, sweetAlert, $location) {
			
				var authenticate = function(credentials, callback) {
			
					var headers = credentials ? {
						authorization : "Basic "
								+ btoa(credentials.username + ":"
										+ credentials.password)
					} : {};
			
					$scope.user = ''
					$http.get('user', {
						headers : headers
					}).success(function(data) {
						if (data.name) {
							$scope.authenticated = true;
							$scope.user = data.name;
							
							if( this.location.hash == "#/logar"){
								document.location.href = "/#/principal";
							}
							
						} else {
							$scope.authenticated = false;
						}
						callback && callback(true);
					}).error(function() {
						$scope.authenticated = false;
						callback && callback(false);
					});
			
				}
			
				authenticate();
			
				$scope.credentials = {};
				$scope.login = function() {
					authenticate($scope.credentials, function(authenticated) {
						$scope.authenticated = authenticated;
						$scope.error = !authenticated;
						
						if( $scope.error ){
							sweetAlert.swal({
					            title: "Error",
					            text: "Login ou senha inválida."
							});
						}else{
							document.location.href = "/#/principal";
						}
					})
				};
			
				$scope.logout = function() {
					$http.post('logout', {}).success(function() {
						$scope.authenticated = false;
						document.location.href="/#/logar";
					}).error(function(data) {
						console.log("Logout failed")
						$scope.authenticated = false;
					});
				}
			
			})

	
	
})();

