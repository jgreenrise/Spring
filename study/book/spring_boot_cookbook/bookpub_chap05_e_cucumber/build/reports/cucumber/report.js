$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("org/test/bookpub/repositories.feature");
formatter.feature({
  "line": 2,
  "name": "Finding a book by ISBN",
  "description": "",
  "id": "finding-a-book-by-isbn",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@txn"
    }
  ]
});
formatter.before({
  "duration": 3207685,
  "status": "passed"
});
formatter.before({
  "duration": 8376743,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "Preload DB Mock Data",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "packt-books fixture is loaded",
  "keyword": "Given "
});
formatter.match({
  "arguments": [
    {
      "val": "packt-books",
      "offset": 0
    }
  ],
  "location": "RepositoryStepdefs.data_fixture_is_loaded(String)"
});
formatter.result({
  "duration": 80772033,
  "status": "passed"
});
formatter.scenario({
  "line": 6,
  "name": "Load one book",
  "description": "",
  "id": "finding-a-book-by-isbn;load-one-book",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 7,
  "name": "3 books available in the catalogue",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "searching for book by isbn 9783",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "book title will be Orchestrating Docker",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "3",
      "offset": 0
    }
  ],
  "location": "RepositoryStepdefs.books_available_in_the_catalogue(int)"
});
formatter.result({
  "duration": 1359514,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "9783",
      "offset": 27
    }
  ],
  "location": "RepositoryStepdefs.searching_for_book_by_isbn(String)"
});
formatter.result({
  "duration": 3216546,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Orchestrating Docker",
      "offset": 19
    }
  ],
  "location": "RepositoryStepdefs.book_title_will_be(String)"
});
formatter.result({
  "duration": 44634,
  "status": "passed"
});
formatter.after({
  "duration": 2006143,
  "status": "passed"
});
formatter.uri("org/test/bookpub/restful.feature");
formatter.feature({
  "line": 2,
  "name": "Finding a book via REST API",
  "description": "",
  "id": "finding-a-book-via-rest-api",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@txn"
    }
  ]
});
formatter.scenarioOutline({
  "line": 6,
  "name": "Using RESTful API to lookup books by ISBN",
  "description": "",
  "id": "finding-a-book-via-rest-api;using-restful-api-to-lookup-books-by-isbn",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 7,
  "name": "catalogue with books",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "requesting url /books/\u003cisbn\u003e",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "status code will be 200",
  "keyword": "Then "
});
formatter.step({
  "line": 10,
  "name": "response content contains \u003ctitle\u003e",
  "keyword": "And "
});
formatter.examples({
  "line": 12,
  "name": "",
  "description": "",
  "id": "finding-a-book-via-rest-api;using-restful-api-to-lookup-books-by-isbn;",
  "rows": [
    {
      "cells": [
        "isbn",
        "title"
      ],
      "line": 13,
      "id": "finding-a-book-via-rest-api;using-restful-api-to-lookup-books-by-isbn;;1"
    },
    {
      "cells": [
        "9781",
        "Romeo and Juliet"
      ],
      "line": 14,
      "id": "finding-a-book-via-rest-api;using-restful-api-to-lookup-books-by-isbn;;2"
    },
    {
      "cells": [
        "9783",
        "Orchestrating Docker"
      ],
      "line": 15,
      "id": "finding-a-book-via-rest-api;using-restful-api-to-lookup-books-by-isbn;;3"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 259479,
  "status": "passed"
});
formatter.before({
  "duration": 2432227,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "packt-books fixture is loaded",
  "keyword": "Given "
});
formatter.match({
  "arguments": [
    {
      "val": "packt-books",
      "offset": 0
    }
  ],
  "location": "RepositoryStepdefs.data_fixture_is_loaded(String)"
});
formatter.result({
  "duration": 937752,
  "status": "passed"
});
formatter.scenario({
  "line": 14,
  "name": "Using RESTful API to lookup books by ISBN",
  "description": "",
  "id": "finding-a-book-via-rest-api;using-restful-api-to-lookup-books-by-isbn;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@txn"
    }
  ]
});
formatter.step({
  "line": 7,
  "name": "catalogue with books",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "requesting url /books/9781",
  "matchedColumns": [
    0
  ],
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "status code will be 200",
  "keyword": "Then "
});
formatter.step({
  "line": 10,
  "name": "response content contains Romeo and Juliet",
  "matchedColumns": [
    1
  ],
  "keyword": "And "
});
formatter.match({
  "location": "RestfulStepdefs.catalogue_with_books()"
});
formatter.result({
  "duration": 513356,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/books/9781",
      "offset": 15
    }
  ],
  "location": "RestfulStepdefs.requesting_url(String)"
});
formatter.result({
  "duration": 10776372,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 20
    }
  ],
  "location": "RestfulStepdefs.status_code_will_be(int)"
});
formatter.result({
  "duration": 671189,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Romeo and Juliet",
      "offset": 26
    }
  ],
  "location": "RestfulStepdefs.response_content_contains(String)"
});
formatter.result({
  "duration": 924993,
  "status": "passed"
});
formatter.after({
  "duration": 533601,
  "status": "passed"
});
formatter.before({
  "duration": 246438,
  "status": "passed"
});
formatter.before({
  "duration": 2480649,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "packt-books fixture is loaded",
  "keyword": "Given "
});
formatter.match({
  "arguments": [
    {
      "val": "packt-books",
      "offset": 0
    }
  ],
  "location": "RepositoryStepdefs.data_fixture_is_loaded(String)"
});
formatter.result({
  "duration": 907151,
  "status": "passed"
});
formatter.scenario({
  "line": 15,
  "name": "Using RESTful API to lookup books by ISBN",
  "description": "",
  "id": "finding-a-book-via-rest-api;using-restful-api-to-lookup-books-by-isbn;;3",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@txn"
    }
  ]
});
formatter.step({
  "line": 7,
  "name": "catalogue with books",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "requesting url /books/9783",
  "matchedColumns": [
    0
  ],
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "status code will be 200",
  "keyword": "Then "
});
formatter.step({
  "line": 10,
  "name": "response content contains Orchestrating Docker",
  "matchedColumns": [
    1
  ],
  "keyword": "And "
});
formatter.match({
  "location": "RestfulStepdefs.catalogue_with_books()"
});
formatter.result({
  "duration": 374438,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/books/9783",
      "offset": 15
    }
  ],
  "location": "RestfulStepdefs.requesting_url(String)"
});
formatter.result({
  "duration": 6228460,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 20
    }
  ],
  "location": "RestfulStepdefs.status_code_will_be(int)"
});
formatter.result({
  "duration": 74689,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Orchestrating Docker",
      "offset": 26
    }
  ],
  "location": "RestfulStepdefs.response_content_contains(String)"
});
formatter.result({
  "duration": 72220,
  "status": "passed"
});
formatter.after({
  "duration": 441127,
  "status": "passed"
});
});