
function configState($stateProvider, $urlRouterProvider, $compileProvider) {

    $compileProvider.debugInfoEnabled(true);

    // Set default state
    $urlRouterProvider.otherwise("logar");
    $stateProvider

		.state('commonlogin', {
            url: "/logar",
            templateUrl: "logar.html",
            data: {
                pageTitle: 'Entre com os dados de acesso',
                specialClass: 'blank'
            }
	    })
	    
	    .state('principal', {
	    	url: "/principal",
	    	templateUrl: "page/index.html",
	    	data: {
	    		pageTitle: 'Seja bem vindo Mariazinha',
	    		specialClass: 'blank'
	    	}
	    })
	    
	    .state('app_views', {
            abstract: true,
            url: "/app_views",
            templateUrl: "genericos/content.html",
            data: {
                pageTitle: 'Configurações'
            }
        })
	    
	    
	    .state('app_views.produtos', {
            url: "/produtos",
            templateUrl: "page/produtos.html",
            data: {
                pageTitle: 'Produtos',
            }
	    })
	    
	    .state('app_views.produtoscad', {
            url: "/produtoscad",
            templateUrl: "page/produtoscad.html",
            params: {produto:null},
            data: {
                pageTitle: 'Produto',
            }
	    })
	    
	    .state('app_views.listacompras', {
            url: "/listacompras",
            templateUrl: "page/listaCompras.html",
            data: {
                pageTitle: 'Lista de Compras',
            }
	    })
	    
	    .state('app_views.listacomprascad', {
            url: "/listacomprascad",
            templateUrl: "page/listaComprascad.html",
            params: {lista:null},
            data: {
                pageTitle: 'Lista de Compras',
            }
	    })
	    
}

angular
    .module('homer')
    .config(configState)
    .run(function($rootScope, $state, editableOptions) {
        $rootScope.$state = $state;
        editableOptions.theme = 'bs3';
    });