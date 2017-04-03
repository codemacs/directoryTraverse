directory traverse project
 
This project contains 
1.directory-traverse-common - a simple java POJO application
2.directory-traverse-service - Spring boot app hosting a REST service
3.directory-traverse-client - simple java interactive console application

instruction for installation:

1.checkout project

2.go to checkout directory and open command prompt

2.run command "mvn clean compile install" 
        -> this will build all the three applications directory-traverse-common , directory-traverse-service and directory-traverse-client

3.run spring boot service as "java -jar directory-traverse-service\target\directory-traverse-service-0.0.1-SNAPSHOT.jar"
        -> tomcat container will be started with default port 8080 and default host localhost

4.open another comman prompt and run command "java -jar directory-traverse-client\target\directory-traverse-client-0.0.1-SNAPSHOT.jar"
		-> it will start a interactive command line program which will ask you 3 inputs enter those values as shown below
			Please enter service host address:
			localhost
			Please enter service host port:
			8080
			Please enter service directory path
			F:\\install\\workspace\\TestData

			->we stared REST service in step 3 on localhost:8080 hence we entered host adress=localhost and host port=8080
			->we have entered service directory path=F:\\install\\workspace\\TestData
			  this client application will traverse through this directory and show output on console as below:
			  
				===============================================================================================

				**** LONG FILES ****
				TestData
					PublicTasks
						TestData
							Germany.txt 29397 die 1151 bei 61 ist 179 den 377 dem 209 bis 86 sich 136 wurden 70 der 1244 des 292 zum 81 wurde 133 zur 68 etwa 58 das 330 durch 123 bundesrepublik 65 deutschen 158 im 382 in 654 deutsche 139 als 201 nicht 51 am 95 an 93 es 77 jahr 72 auf 147 einen 54 auch 127 einer 64 sind 128 zu 171 aus 84 deutschland 275 seit 75 prozent 55 deutschlands 51 ein 99 unter 84 wie 73 von 356 mit 235 für 137 über 61 und 994 nach 120 oder 75 eine 139 sowie 77 
							a
								Berlin.txt 15581 die 556 ist 103 ein 55 den 171 dem 108 sich 75 der 726 des 161 von 198 wurde 60 das 185 mit 166 berlin 296 für 60 durch 51 im 216 berlins 53 in 409 über 55 als 96 und 524 nach 74 am 61 an 58 auf 74 eine 74 auch 55 sind 62 stadt 64 berliner 117 zu 79 aus 57 
								b
									e.txt 1260 the 115 and 58 of 119 
									lamar.txt 1921 the 119 in 61 and 63 of 65 
									Wigan.txt 5485 a 106 in 60 for 60 the 260 2009. 54 january 69 and 72 of 61 from 69 to 95 up 74 wigan 108 ^ 73 on 104 jump 68 
									d
										Buchdruck.txt 3787 der 177 die 130 in 95 das 51 und 111 


				**** SHORT FILES ****
				TestData
					PublicTasks
						TestData
							Andromeda.txt 307
							Mordellistena.txt 49
							Reynolds.txt 892 the 60 
							a
								Lomond.txt 142
								b
									d
										Talmadge.txt 170

				===============================================================================================
				

Testing REST service :

Assuming service is up on localhost:8080

Service URL   : http://localhost:8080/api/service/getdirectorydetails
Method        : POST
Content-Type  : application/json;charset=UTF-8


sample JSON request:

{
	"directoryPath" : "F:\\install\\workspace\\TestData"
}

sample JSON response:

{
    "longFiles": {
        "directoryName": "TestData",
        "files": [],
        "subDirectories": [
            {
                "directoryName": "PublicTasks",
                "files": [],
                "subDirectories": [
                    {
                        "directoryName": "TestData",
                        "files": [
                            {
                                "fileName": "Germany.txt",
                                "absoluteFilePath": "F:\\install\\workspace\\TestData\\PublicTasks\\TestData\\Germany.txt",
                                "wordCount": 29397,
                                "wordsRepeatingMoreThan50Times": {
                                    "die": 1151,
                                    "bei": 61,
                                    "ist": 179,
                                    "den": 377,
                                    "dem": 209,
                                    "bis": 86,
                                    "sich": 136,
                                    "wurden": 70,
                                    "der": 1244,
                                    "des": 292,
                                    "zum": 81,
                                    "wurde": 133,
                                    "zur": 68,
                                    "etwa": 58,
                                    "das": 330,
                                    "durch": 123,
                                    "bundesrepublik": 65,
                                    "deutschen": 158,
                                    "im": 382,
                                    "in": 654,
                                    "deutsche": 139,
                                    "als": 201,
                                    "nicht": 51,
                                    "am": 95,
                                    "an": 93,
                                    "es": 77,
                                    "jahr": 72,
                                    "auf": 147,
                                    "einen": 54,
                                    "auch": 127,
                                    "einer": 64,
                                    "sind": 128,
                                    "zu": 171,
                                    "aus": 84,
                                    "deutschland": 275,
                                    "seit": 75,
                                    "prozent": 55,
                                    "deutschlands": 51,
                                    "ein": 99,
                                    "unter": 84,
                                    "wie": 73,
                                    "von": 356,
                                    "mit": 235,
                                    "für": 137,
                                    "über": 61,
                                    "und": 994,
                                    "nach": 120,
                                    "oder": 75,
                                    "eine": 139,
                                    "sowie": 77
                                }
                            }
                        ],
                        "subDirectories": [
                            {
                                "directoryName": "a",
                                "files": [
                                    {
                                        "fileName": "Berlin.txt",
                                        "absoluteFilePath": "F:\\install\\workspace\\TestData\\PublicTasks\\TestData\\a\\Berlin.txt",
                                        "wordCount": 15581,
                                        "wordsRepeatingMoreThan50Times": {
                                            "die": 556,
                                            "ist": 103,
                                            "ein": 55,
                                            "den": 171,
                                            "dem": 108,
                                            "sich": 75,
                                            "der": 726,
                                            "des": 161,
                                            "von": 198,
                                            "wurde": 60,
                                            "das": 185,
                                            "mit": 166,
                                            "berlin": 296,
                                            "für": 60,
                                            "durch": 51,
                                            "im": 216,
                                            "berlins": 53,
                                            "in": 409,
                                            "über": 55,
                                            "als": 96,
                                            "und": 524,
                                            "nach": 74,
                                            "am": 61,
                                            "an": 58,
                                            "auf": 74,
                                            "eine": 74,
                                            "auch": 55,
                                            "sind": 62,
                                            "stadt": 64,
                                            "berliner": 117,
                                            "zu": 79,
                                            "aus": 57
                                        }
                                    }
                                ],
                                "subDirectories": [
                                    {
                                        "directoryName": "b",
                                        "files": [
                                            {
                                                "fileName": "e.txt",
                                                "absoluteFilePath": "F:\\install\\workspace\\TestData\\PublicTasks\\TestData\\a\\b\\e.txt",
                                                "wordCount": 1260,
                                                "wordsRepeatingMoreThan50Times": {
                                                    "the": 115,
                                                    "and": 58,
                                                    "of": 119
                                                }
                                            },
                                            {
                                                "fileName": "lamar.txt",
                                                "absoluteFilePath": "F:\\install\\workspace\\TestData\\PublicTasks\\TestData\\a\\b\\lamar.txt",
                                                "wordCount": 1921,
                                                "wordsRepeatingMoreThan50Times": {
                                                    "the": 119,
                                                    "in": 61,
                                                    "and": 63,
                                                    "of": 65
                                                }
                                            },
                                            {
                                                "fileName": "Wigan.txt",
                                                "absoluteFilePath": "F:\\install\\workspace\\TestData\\PublicTasks\\TestData\\a\\b\\Wigan.txt",
                                                "wordCount": 5485,
                                                "wordsRepeatingMoreThan50Times": {
                                                    "a": 106,
                                                    "in": 60,
                                                    "for": 60,
                                                    "the": 260,
                                                    "2009.": 54,
                                                    "january": 69,
                                                    "and": 72,
                                                    "of": 61,
                                                    "from": 69,
                                                    "to": 95,
                                                    "up": 74,
                                                    "wigan": 108,
                                                    "^": 73,
                                                    "on": 104,
                                                    "jump": 68
                                                }
                                            }
                                        ],
                                        "subDirectories": [
                                            {
                                                "directoryName": "d",
                                                "files": [
                                                    {
                                                        "fileName": "Buchdruck.txt",
                                                        "absoluteFilePath": "F:\\install\\workspace\\TestData\\PublicTasks\\TestData\\a\\b\\d\\Buchdruck.txt",
                                                        "wordCount": 3787,
                                                        "wordsRepeatingMoreThan50Times": {
                                                            "der": 177,
                                                            "die": 130,
                                                            "in": 95,
                                                            "das": 51,
                                                            "und": 111
                                                        }
                                                    }
                                                ],
                                                "subDirectories": []
                                            }
                                        ]
                                    }
                                ]
                            }
                        ]
                    }
                ]
            }
        ]
    },
    "shortFiles": {
        "directoryName": "TestData",
        "files": [],
        "subDirectories": [
            {
                "directoryName": "PublicTasks",
                "files": [],
                "subDirectories": [
                    {
                        "directoryName": "TestData",
                        "files": [
                            {
                                "fileName": "Andromeda.txt",
                                "absoluteFilePath": "F:\\install\\workspace\\TestData\\PublicTasks\\TestData\\Andromeda.txt",
                                "wordCount": 307,
                                "wordsRepeatingMoreThan50Times": {}
                            },
                            {
                                "fileName": "Mordellistena.txt",
                                "absoluteFilePath": "F:\\install\\workspace\\TestData\\PublicTasks\\TestData\\Mordellistena.txt",
                                "wordCount": 49,
                                "wordsRepeatingMoreThan50Times": {}
                            },
                            {
                                "fileName": "Reynolds.txt",
                                "absoluteFilePath": "F:\\install\\workspace\\TestData\\PublicTasks\\TestData\\Reynolds.txt",
                                "wordCount": 892,
                                "wordsRepeatingMoreThan50Times": {
                                    "the": 60
                                }
                            }
                        ],
                        "subDirectories": [
                            {
                                "directoryName": "a",
                                "files": [
                                    {
                                        "fileName": "Lomond.txt",
                                        "absoluteFilePath": "F:\\install\\workspace\\TestData\\PublicTasks\\TestData\\a\\Lomond.txt",
                                        "wordCount": 142,
                                        "wordsRepeatingMoreThan50Times": {}
                                    }
                                ],
                                "subDirectories": [
                                    {
                                        "directoryName": "b",
                                        "files": [],
                                        "subDirectories": [
                                            {
                                                "directoryName": "d",
                                                "files": [
                                                    {
                                                        "fileName": "Talmadge.txt",
                                                        "absoluteFilePath": "F:\\install\\workspace\\TestData\\PublicTasks\\TestData\\a\\b\\d\\Talmadge.txt",
                                                        "wordCount": 170,
                                                        "wordsRepeatingMoreThan50Times": {}
                                                    }
                                                ],
                                                "subDirectories": []
                                            }
                                        ]
                                    }
                                ]
                            }
                        ]
                    }
                ]
            }
        ]
    }
}
