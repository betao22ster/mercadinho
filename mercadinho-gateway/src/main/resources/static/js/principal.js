angular
    .module('homer')
	.controller('principal', 
	function($scope, $http, sweetAlert, $location, $stateParams) {

		$http.get("/servico/principal/carregar").success(function(data) {
			
			console.log("consulta ok");
			$scope.dados = data;
			
		}).error(function(data) {
			console.log("failed");
			
			sweetAlert.swal({
	            title: "Alerta",
	            text: data.message
			});
			
		});
		
	}
);