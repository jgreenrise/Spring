// create our angular app and inject ngAnimate and ui-router 
// =============================================================================
angular
		.module('formApp', [ 'ngAnimate', 'ui.router', 'oi.multiselect' ])

		// configuring our routes
		// =============================================================================
		.config(function($stateProvider, $urlRouterProvider) {

			$stateProvider

			// route to show our basic form (/form)
			.state('form', {
				url : '/form',
				templateUrl : 'form.html',
				controller : 'formController'
			})

			// nested states
			// each of these sections will have their own view
			// url will be nested (/form/profile)
			.state('form.contactInformation', {
				url : '/contactInformation',
				templateUrl : 'form-contact-information.html'
			})

			.state('form.professsionalInformation', {
				url : '/professionalInformation',
				templateUrl : 'form-professional-information.html'
			})

			.state('form.education', {
				url : '/education',
				templateUrl : 'form-profile-education.html'
			})

			.state('form.clinicInformation', {
				url : '/clinicInfo',
				templateUrl : 'form-clinic-information.html'
			})

			.state('form.uploadPicture', {
				url : '/uploadProfilePicture',
				templateUrl : 'form-doc-verification-photo.html'
			})

			// nested states
			// each of these sections will have their own view
			// url will be nested (/form/profile)
			.state('form.profile', {
				url : '/profile',
				templateUrl : 'form-profile.html'
			})

			// url will be /form/interests
			.state('form.interests', {
				url : '/interests',
				templateUrl : 'form-interests.html'
			})

			// url will be /form/payment
			.state('form.payment', {
				url : '/payment',
				templateUrl : 'form-payment.html'
			});

			// catch all route
			// send users to the form page
			$urlRouterProvider.otherwise('/form/contactInformation');
		})

		// our controller for the form
		// =============================================================================
		.controller(
				'formController',
				function($scope) {

					$scope.specialities = [ 'Acupressure', 'Acupuncture',
							'Aesthetic Medicine', 'Allergy/Immunology',
							'Alternative Medicine', 'Anesthesiology',
							'Audiology', 'Ayurveda', 'Bariatrics',
							'Cardiology', 'Cosmetic/Plastic Surgery',
							'Dentistry', 'Dietician/Nutritionist',
							'Ear-Nose-Throat (ENT)', 'Endocrinology',
							'Epidemology', 'Gastroentology',
							'General Physician', 'Genetics', 'Geriatrics',
							'Gynaecology', 'Hematology', 'Homeopathy',
							'Integrative Medicine', 'Nephrology', 'Neurology',
							'Neurosurgery', 'Nuclear Medicine Physician',
							'Occupational Therapy', 'Oncology', 'Opthalmology',
							'Orthopaedics', 'Others', 'Pathology',
							'Pediatrics', 'Phlebology', 'Physiotherapy',
							'Proctology', 'Psychiatry', 'Psycology',
							'Pulmonology', 'Radiology', 'Rheumatology',
							'Sexology', 'Somnology', 'Speech Therapy',
							'Toxicology', 'Unani', 'Urology', 'Venereology',
							'Veterinary' ];

					// we will store all of our form data in this object
					$scope.formData = {};

					// function to process the form
					$scope.processForm = function(form) {

						$scope.alert_message_patient_invalidEmail = "";

						if ($scope.price == null) {
							alert('114');
							$scope.alert_message_patient_invalidEmail = "Please enter your email id";
						} else {
							alert('117');
							alert('awesome!');
						}

					};

				});
