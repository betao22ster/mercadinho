angular.module('homer').controller(
		'listaComprascad',
		function($scope, $http, sweetAlert, $location, $stateParams) {

			$scope.lista = {};

			$scope.novo = function() {
				$scope.lista = {};
				$scope.inclusao = true;
				$scope.signup_form.submitted = false;
			}

			$scope.gravar = function() {

				$scope.signup_form.submitted = true;

				if (!$scope.signup_form.$valid) {

					return null;
				}

				sweetAlert.swal({
					title : "Confirmação",
					text : "Deseja confirmar?",
					showCancelButton : true,
					confirmButtonColor : "#DD6B55",
					confirmButtonText : "Sim",
					cancelButtonText : "Não",
					closeOnConfirm : true,
					closeOnCancel : true
				}, function(isConfirm) {

					if (isConfirm) {
						$scope.gravarOK();
					}

				});
			}

			$scope.gravarOK = function() {

				$scope.processoGravacao = {};
				$scope.processoGravacao.link = '/servico/lcompras/incluir';

				if ($scope.lista.id != null) {
					console.log("Modo alteração");
					$scope.processoGravacao.link = '/servico/lcompras/alterar';
				}

				$http.post($scope.processoGravacao.link, $scope.lista).success(
						function(data) {
							console.log("gravacao ok");

							$scope.lista = data;

							$scope.carregarProdutos();

							sweetAlert.swal({
								title : "Informação",
								text : "Registro gravado com sucesso."
							});

							$scope.inclusao = false;

						}).error(function(data) {
					console.log("failed");

					sweetAlert.swal({
						title : "Alerta",
						text : data.message
					});

				});

			}

			$scope.listaProduto = [];

			$scope.carregarProdutos = function() {
				$http.get("/servico/produto/findAll").success(function(data) {

					console.log("consulta ok");
					$scope.listaProduto = data;

				}).error(function(data) {
					console.log("failed");

					sweetAlert.swal({
						title : "Alerta",
						text : data.message
					});

				});
			}

			$scope.prodSel;
			$scope.listaProdutoSel = [];

			$scope.selCroduto = function(item) {

				$http.get(
						"/servico/lcompras/incluirprod/" + item.id + "/"
								+ $scope.lista.id).success(function(data) {

					console.log("add ok");
					$scope.listaProdutoSel.push(item);

				}).error(function(data) {
					console.log("failed");

					sweetAlert.swal({
						title : "Alerta",
						text : data.message
					});

				});

			}

			$scope.carregarListaProdutos = function() {

				$http.get("/servico/lcompras/findallprod/" + $scope.lista.id)
						.success(function(data) {

							console.log("consulta ok");
							var loop = 0;
							for (loop = 0; loop < data.length; loop++) {
								$scope.listaProdutoSel.push(data[loop]);
							}

						}).error(function(data) {
							console.log("failed");

							sweetAlert.swal({
								title : "Alerta",
								text : data.message
							});

						});

			}

			if ($stateParams.lista != null) {
				$scope.lista = $stateParams.lista;
				$scope.carregarProdutos();
				$scope.carregarListaProdutos();

			}

		});