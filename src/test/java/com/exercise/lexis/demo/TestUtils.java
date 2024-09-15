package com.exercise.lexis.demo;

public class TestUtils {

    private TestUtils(){}
    public static final String companyResponse = """
			{
			    "page_number": 1,
			    "kind": "search#companies",
			    "total_results": 20,
			    "items": [
			        {
			            "company_status": "active",
			            "address_snippet": "Boswell Cottage Main Street, North Leverton, Retford, England, DN22 0AD",
			            "date_of_creation": "2008-02-11",
			            "matches": {
			                "title": [
			                    1,
			                    3
			                ]
			            },
			            "description": "06500244 - Incorporated on 11 February 2008",
			            "links": {
			                "self": "/company/06500244"
			            },
			            "company_number": "06500244",
			            "title": "BBC LIMITED",
			            "company_type": "ltd",
			            "address": {
			                "premises": "Boswell Cottage Main Street",
			                "postal_code": "DN22 0AD",
			                "country": "England",
			                "locality": "Retford",
			                "address_line_1": "North Leverton"
			            },
			            "kind": "searchresults#company",
			            "description_identifier": [
			                "incorporated-on"
			            ]
			        }]
			  }
			  """;

	public static final String officerResponse = """
			{
			    "etag": "6dd2261e61776d79c2c50685145fac364e75e24e",
			    "links": {
			        "self": "/company/10241297/officers"
			    },
			    "kind": "officer-list",
			    "items_per_page": 35,
			    "items": [
			        {
			            "address": {
			                "premises": "The Leeming Building",
			                "postal_code": "LS2 7JF",
			                "country": "England",
			                "locality": "Leeds",
			                "address_line_1": "Vicar Lane"
			            },
			            "name": "ANTLES, Kerri",
			            "appointed_on": "2017-04-01",
			            "officer_role": "director",
			            "links": {
			                "officer": {
			                    "appointments": "/officers/4R8_9bZ44w0_cRlrxoC-wRwaMiE/appointments"
			                }
			            },
			            "date_of_birth": {
			                "month": 6,
			                "year": 1969
			            },
			            "occupation": "Finance And Accounting",
			            "country_of_residence": "United States",
			            "nationality": "American"
			        }]
			  }
			  """;

	public static final String searchRequest = """
			{
			    "companyName" : "BBC LIMITED",
			    "companyNumber" : "06500244"
			}
			""";

	public static final String response = """
			{
			  "totalResults": 20,
			  "items": [
			    {
			      "title": "BBC LIMITED",
			      "address": {
			        "premises": "Boswell Cottage Main Street",
			        "locality": "Retford",
			        "country": "England",
			        "address_line_1": "North Leverton",
			        "postal_code": "DN22 0AD"
			      },
			      "officers": [
			        {
			          "name": "ANTLES, Kerri",
			          "address": {
			            "premises": "The Leeming Building",
			            "locality": "Leeds",
			            "country": "England",
			            "address_line_1": "Vicar Lane",
			            "postal_code": "LS2 7JF"
			          },
			          "officer_role": "director",
			          "appointed_on": "2017-04-01"
			        }
			      ],
			      "company_number": "06500244",
			      "company_type": "ltd",
			      "company_status": "active",
			      "date_of_creation": "2008-02-11"
			    }
			  ]
			}
			""";
}
