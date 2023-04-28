@support
Feature: Testcases for Support view Create Ticket

  Background: Check login functionality
    Given Navigate to helpdesk and login
    Then Verify if login is successful
    And Switch to view "Support View"

  Scenario Outline: Verify unassigned incident ticket creation for own caller with file upload
    Given Create ticket with file "attached" and type "Incident", caller "Abhishek Gautam", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Then Submit and verify ticket with file "attached" and type "Incident", caller "Abhishek Gautam", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Examples:
      | dept     | category            | subcategory                  |
      | IT       | End User Hardware   | Desktop                      |
      | IT       | End User Hardware   | Deskphones                   |
      | IT       | End User Hardware   | Laptop                       |
      | IT       | End User Hardware   | Docking Station              |
      | IT       | End User Hardware   | Monitor                      |
      | IT       | End User Hardware   | Printer                      |
      | IT       | End User Hardware   | Peripherals                  |
      | IT       | Access and Security | Network Share                |
      | IT       | Access and Security | MFA                          |
      | IT       | Access and Security | Windows                      |
      | IT       | Access and Security | Others                       |
      | IT       | Access and Security | Linux                        |
      | IT       | Enterprise Hardware | Firewall                     |
      | IT       | Enterprise Hardware | Router                       |
      | IT       | Enterprise Hardware | Server                       |
      | IT       | Enterprise Hardware | Switch                       |
      | IT       | Enterprise Hardware | VMs                          |
      | IT       | Enterprise Software | Corporate applications       |
      | IT       | Enterprise Software | Database                     |
      | IT       | Enterprise Software | Enterprise resource Planning |
      | IT       | Enterprise Software | Operating system             |
      | IT       | End User Software   | Corporate Applications       |
      | IT       | End User Software   | Office Suite                 |
      | IT       | End User Software   | Operating System             |
      | IT       | End User Software   | Web Browser                  |
      | Accounts | Employee Info       | LTA                          |
      | HR       | Joining             | Welcome Kit allocation       |
      | Admin    | Facility            | Courier                      |

  Scenario Outline: Verify assigned incident ticket creation for own caller with file upload
    Given Create ticket with file "attached" and type "Incident", caller "Abhishek Gautam", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Then Submit and verify ticket with file "attached" and type "Incident", caller "Abhishek Gautam", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Examples:
      | dept | category            | subcategory                  |
      | IT   | End User Hardware   | Desktop                      |
      | IT   | End User Hardware   | Deskphones                   |
      | IT   | End User Hardware   | Laptop                       |
      | IT   | End User Hardware   | Docking Station              |
      | IT   | End User Hardware   | Monitor                      |
      | IT   | End User Hardware   | Printer                      |
      | IT   | End User Hardware   | Peripherals                  |
      | IT   | Access and Security | Network Share                |
      | IT   | Access and Security | MFA                          |
      | IT   | Access and Security | Windows                      |
      | IT   | Access and Security | Others                       |
      | IT   | Access and Security | Linux                        |
      | IT   | Enterprise Hardware | Firewall                     |
      | IT   | Enterprise Hardware | Router                       |
      | IT   | Enterprise Hardware | Server                       |
      | IT   | Enterprise Hardware | Switch                       |
      | IT   | Enterprise Hardware | VMs                          |
      | IT   | Enterprise Software | Corporate applications       |
      | IT   | Enterprise Software | Database                     |
      | IT   | Enterprise Software | Enterprise resource Planning |
      | IT   | Enterprise Software | Operating system             |
      | IT   | End User Software   | Corporate Applications       |
      | IT   | End User Software   | Office Suite                 |
      | IT   | End User Software   | Operating System             |
      | IT   | End User Software   | Web Browser                  |

  Scenario Outline: Verify unassigned incident ticket creation for own caller without file upload
    Given Create ticket with file "unattached" and type "Incident", caller "Abhishek Gautam", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Then Submit and verify ticket with file "unattached" and type "Incident", caller "Abhishek Gautam", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Examples:
      | dept     | category            | subcategory                  |
      | IT       | End User Hardware   | Desktop                      |
      | IT       | End User Hardware   | Deskphones                   |
      | IT       | End User Hardware   | Laptop                       |
      | IT       | End User Hardware   | Docking Station              |
      | IT       | End User Hardware   | Monitor                      |
      | IT       | End User Hardware   | Printer                      |
      | IT       | End User Hardware   | Peripherals                  |
      | IT       | Access and Security | Network Share                |
      | IT       | Access and Security | MFA                          |
      | IT       | Access and Security | Windows                      |
      | IT       | Access and Security | Others                       |
      | IT       | Access and Security | Linux                        |
      | IT       | Enterprise Hardware | Firewall                     |
      | IT       | Enterprise Hardware | Router                       |
      | IT       | Enterprise Hardware | Server                       |
      | IT       | Enterprise Hardware | Switch                       |
      | IT       | Enterprise Hardware | VMs                          |
      | IT       | Enterprise Software | Corporate applications       |
      | IT       | Enterprise Software | Database                     |
      | IT       | Enterprise Software | Enterprise resource Planning |
      | IT       | Enterprise Software | Operating system             |
      | IT       | End User Software   | Corporate Applications       |
      | IT       | End User Software   | Office Suite                 |
      | IT       | End User Software   | Operating System             |
      | IT       | End User Software   | Web Browser                  |
      | Accounts | Employee Info       | LTA                          |
      | HR       | Joining             | Welcome Kit allocation       |
      | Admin    | Facility            | Courier                      |

  Scenario Outline: Verify assigned incident ticket creation for own caller without file upload
    Given Create ticket with file "unattached" and type "Incident", caller "Abhishek Gautam", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Then Submit and verify ticket with file "unattached" and type "Incident", caller "Abhishek Gautam", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Examples:
      | dept | category            | subcategory                  |
      | IT   | End User Hardware   | Desktop                      |
      | IT   | End User Hardware   | Deskphones                   |
      | IT   | End User Hardware   | Laptop                       |
      | IT   | End User Hardware   | Docking Station              |
      | IT   | End User Hardware   | Monitor                      |
      | IT   | End User Hardware   | Printer                      |
      | IT   | End User Hardware   | Peripherals                  |
      | IT   | Access and Security | Network Share                |
      | IT   | Access and Security | MFA                          |
      | IT   | Access and Security | Windows                      |
      | IT   | Access and Security | Others                       |
      | IT   | Access and Security | Linux                        |
      | IT   | Enterprise Hardware | Firewall                     |
      | IT   | Enterprise Hardware | Router                       |
      | IT   | Enterprise Hardware | Server                       |
      | IT   | Enterprise Hardware | Switch                       |
      | IT   | Enterprise Hardware | VMs                          |
      | IT   | Enterprise Software | Corporate applications       |
      | IT   | Enterprise Software | Database                     |
      | IT   | Enterprise Software | Enterprise resource Planning |
      | IT   | Enterprise Software | Operating system             |
      | IT   | End User Software   | Corporate Applications       |
      | IT   | End User Software   | Office Suite                 |
      | IT   | End User Software   | Operating System             |
      | IT   | End User Software   | Web Browser                  |


  Scenario Outline: Verify unassigned incident ticket creation for other caller with file upload
    Given Create ticket with file "attached" and type "Incident", caller "Sheeza Bakshi", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Then Submit and verify ticket with file "attached" and type "Incident", caller "Sheeza Bakshi", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Examples:
      | dept     | category            | subcategory                  |
      | IT       | End User Hardware   | Desktop                      |
      | IT       | End User Hardware   | Deskphones                   |
      | IT       | End User Hardware   | Laptop                       |
      | IT       | End User Hardware   | Docking Station              |
      | IT       | End User Hardware   | Monitor                      |
      | IT       | End User Hardware   | Printer                      |
      | IT       | End User Hardware   | Peripherals                  |
      | IT       | Access and Security | Network Share                |
      | IT       | Access and Security | MFA                          |
      | IT       | Access and Security | Windows                      |
      | IT       | Access and Security | Others                       |
      | IT       | Access and Security | Linux                        |
      | IT       | Enterprise Hardware | Firewall                     |
      | IT       | Enterprise Hardware | Router                       |
      | IT       | Enterprise Hardware | Server                       |
      | IT       | Enterprise Hardware | Switch                       |
      | IT       | Enterprise Hardware | VMs                          |
      | IT       | Enterprise Software | Corporate applications       |
      | IT       | Enterprise Software | Database                     |
      | IT       | Enterprise Software | Enterprise resource Planning |
      | IT       | Enterprise Software | Operating system             |
      | IT       | End User Software   | Corporate Applications       |
      | IT       | End User Software   | Office Suite                 |
      | IT       | End User Software   | Operating System             |
      | IT       | End User Software   | Web Browser                  |
      | Accounts | Employee Info       | LTA                          |
      | HR       | Joining             | Welcome Kit allocation       |
      | Admin    | Facility            | Courier                      |


  Scenario Outline: Verify assigned incident ticket creation for other caller with file upload
    Given Create ticket with file "attached" and type "Incident", caller "Sheeza Bakshi", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Then Submit and verify ticket with file "attached" and type "Incident", caller "Sheeza Bakshi", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Examples:
      | dept | category            | subcategory                  |
      | IT   | End User Hardware   | Desktop                      |
      | IT   | End User Hardware   | Deskphones                   |
      | IT   | End User Hardware   | Laptop                       |
      | IT   | End User Hardware   | Docking Station              |
      | IT   | End User Hardware   | Monitor                      |
      | IT   | End User Hardware   | Printer                      |
      | IT   | End User Hardware   | Peripherals                  |
      | IT   | Access and Security | Network Share                |
      | IT   | Access and Security | MFA                          |
      | IT   | Access and Security | Windows                      |
      | IT   | Access and Security | Others                       |
      | IT   | Access and Security | Linux                        |
      | IT   | Enterprise Hardware | Firewall                     |
      | IT   | Enterprise Hardware | Router                       |
      | IT   | Enterprise Hardware | Server                       |
      | IT   | Enterprise Hardware | Switch                       |
      | IT   | Enterprise Hardware | VMs                          |
      | IT   | Enterprise Software | Corporate applications       |
      | IT   | Enterprise Software | Database                     |
      | IT   | Enterprise Software | Enterprise resource Planning |
      | IT   | Enterprise Software | Operating system             |
      | IT   | End User Software   | Corporate Applications       |
      | IT   | End User Software   | Office Suite                 |
      | IT   | End User Software   | Operating System             |
      | IT   | End User Software   | Web Browser                  |

  Scenario Outline: Verify unassigned incident ticket creation for other caller without file upload
    Given Create ticket with file "unattached" and type "Incident", caller "Sheeza Bakshi", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Then Submit and verify ticket with file "unattached" and type "Incident", caller "Sheeza Bakshi", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Examples:
      | dept     | category            | subcategory                  |
      | IT       | End User Hardware   | Desktop                      |
      | IT       | End User Hardware   | Deskphones                   |
      | IT       | End User Hardware   | Laptop                       |
      | IT       | End User Hardware   | Docking Station              |
      | IT       | End User Hardware   | Monitor                      |
      | IT       | End User Hardware   | Printer                      |
      | IT       | End User Hardware   | Peripherals                  |
      | IT       | Access and Security | Network Share                |
      | IT       | Access and Security | MFA                          |
      | IT       | Access and Security | Windows                      |
      | IT       | Access and Security | Others                       |
      | IT       | Access and Security | Linux                        |
      | IT       | Enterprise Hardware | Firewall                     |
      | IT       | Enterprise Hardware | Router                       |
      | IT       | Enterprise Hardware | Server                       |
      | IT       | Enterprise Hardware | Switch                       |
      | IT       | Enterprise Hardware | VMs                          |
      | IT       | Enterprise Software | Corporate applications       |
      | IT       | Enterprise Software | Database                     |
      | IT       | Enterprise Software | Enterprise resource Planning |
      | IT       | Enterprise Software | Operating system             |
      | IT       | End User Software   | Corporate Applications       |
      | IT       | End User Software   | Office Suite                 |
      | IT       | End User Software   | Operating System             |
      | IT       | End User Software   | Web Browser                  |
      | Accounts | Employee Info       | LTA                          |
      | HR       | Joining             | Welcome Kit allocation       |
      | Admin    | Facility            | Courier                      |


  Scenario Outline: Verify assigned incident ticket creation for other caller without file upload
    Given Create ticket with file "unattached" and type "Incident", caller "Sheeza Bakshi", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Then Submit and verify ticket with file "unattached" and type "Incident", caller "Sheeza Bakshi", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Examples:
      | dept | category            | subcategory                  |
      | IT   | End User Hardware   | Desktop                      |
      | IT   | End User Hardware   | Deskphones                   |
      | IT   | End User Hardware   | Laptop                       |
      | IT   | End User Hardware   | Docking Station              |
      | IT   | End User Hardware   | Monitor                      |
      | IT   | End User Hardware   | Printer                      |
      | IT   | End User Hardware   | Peripherals                  |
      | IT   | Access and Security | Network Share                |
      | IT   | Access and Security | MFA                          |
      | IT   | Access and Security | Windows                      |
      | IT   | Access and Security | Others                       |
      | IT   | Access and Security | Linux                        |
      | IT   | Enterprise Hardware | Firewall                     |
      | IT   | Enterprise Hardware | Router                       |
      | IT   | Enterprise Hardware | Server                       |
      | IT   | Enterprise Hardware | Switch                       |
      | IT   | Enterprise Hardware | VMs                          |
      | IT   | Enterprise Software | Corporate applications       |
      | IT   | Enterprise Software | Database                     |
      | IT   | Enterprise Software | Enterprise resource Planning |
      | IT   | Enterprise Software | Operating system             |
      | IT   | End User Software   | Corporate Applications       |
      | IT   | End User Software   | Office Suite                 |
      | IT   | End User Software   | Operating System             |
      | IT   | End User Software   | Web Browser                  |


  Scenario Outline: Verify unassigned incident ticket creation for other caller with Phone channel and file upload
    Given Create ticket with file "attached" and type "Incident", caller "Sheeza Bakshi", channel "Phone Call", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Then Submit and verify ticket with file "attached" and type "Incident", caller "Sheeza Bakshi", channel "Phone Call", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Examples:
      | dept     | category            | subcategory                  |
      | IT       | End User Hardware   | Desktop                      |
      | IT       | End User Hardware   | Deskphones                   |
      | IT       | End User Hardware   | Laptop                       |
      | IT       | End User Hardware   | Docking Station              |
      | IT       | End User Hardware   | Monitor                      |
      | IT       | End User Hardware   | Printer                      |
      | IT       | End User Hardware   | Peripherals                  |
      | IT       | Access and Security | Network Share                |
      | IT       | Access and Security | MFA                          |
      | IT       | Access and Security | Windows                      |
      | IT       | Access and Security | Others                       |
      | IT       | Access and Security | Linux                        |
      | IT       | Enterprise Hardware | Firewall                     |
      | IT       | Enterprise Hardware | Router                       |
      | IT       | Enterprise Hardware | Server                       |
      | IT       | Enterprise Hardware | Switch                       |
      | IT       | Enterprise Hardware | VMs                          |
      | IT       | Enterprise Software | Corporate applications       |
      | IT       | Enterprise Software | Database                     |
      | IT       | Enterprise Software | Enterprise resource Planning |
      | IT       | Enterprise Software | Operating system             |
      | IT       | End User Software   | Corporate Applications       |
      | IT       | End User Software   | Office Suite                 |
      | IT       | End User Software   | Operating System             |
      | IT       | End User Software   | Web Browser                  |
      | Accounts | Employee Info       | LTA                          |
      | HR       | Joining             | Welcome Kit allocation       |
      | Admin    | Facility            | Courier                      |


  Scenario Outline: Verify assigned incident ticket creation for other caller with Phone channel and file upload
    Given Create ticket with file "attached" and type "Incident", caller "Sheeza Bakshi", channel "Phone Call", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Then Submit and verify ticket with file "attached" and type "Incident", caller "Sheeza Bakshi", channel "Phone Call", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Examples:
      | dept | category            | subcategory                  |
      | IT   | End User Hardware   | Desktop                      |
      | IT   | End User Hardware   | Deskphones                   |
      | IT   | End User Hardware   | Laptop                       |
      | IT   | End User Hardware   | Docking Station              |
      | IT   | End User Hardware   | Monitor                      |
      | IT   | End User Hardware   | Printer                      |
      | IT   | End User Hardware   | Peripherals                  |
      | IT   | Access and Security | Network Share                |
      | IT   | Access and Security | MFA                          |
      | IT   | Access and Security | Windows                      |
      | IT   | Access and Security | Others                       |
      | IT   | Access and Security | Linux                        |
      | IT   | Enterprise Hardware | Firewall                     |
      | IT   | Enterprise Hardware | Router                       |
      | IT   | Enterprise Hardware | Server                       |
      | IT   | Enterprise Hardware | Switch                       |
      | IT   | Enterprise Hardware | VMs                          |
      | IT   | Enterprise Software | Corporate applications       |
      | IT   | Enterprise Software | Database                     |
      | IT   | Enterprise Software | Enterprise resource Planning |
      | IT   | Enterprise Software | Operating system             |
      | IT   | End User Software   | Corporate Applications       |
      | IT   | End User Software   | Office Suite                 |
      | IT   | End User Software   | Operating System             |
      | IT   | End User Software   | Web Browser                  |

  Scenario Outline: Verify unassigned incident ticket creation for other caller with Phone channel and without file upload
    Given Create ticket with file "unattached" and type "Incident", caller "Sheeza Bakshi", channel "Phone Call", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Then Submit and verify ticket with file "unattached" and type "Incident", caller "Sheeza Bakshi", channel "Phone Call", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Examples:
      | dept     | category            | subcategory                  |
      | IT       | End User Hardware   | Desktop                      |
      | IT       | End User Hardware   | Deskphones                   |
      | IT       | End User Hardware   | Laptop                       |
      | IT       | End User Hardware   | Docking Station              |
      | IT       | End User Hardware   | Monitor                      |
      | IT       | End User Hardware   | Printer                      |
      | IT       | End User Hardware   | Peripherals                  |
      | IT       | Access and Security | Network Share                |
      | IT       | Access and Security | MFA                          |
      | IT       | Access and Security | Windows                      |
      | IT       | Access and Security | Others                       |
      | IT       | Access and Security | Linux                        |
      | IT       | Enterprise Hardware | Firewall                     |
      | IT       | Enterprise Hardware | Router                       |
      | IT       | Enterprise Hardware | Server                       |
      | IT       | Enterprise Hardware | Switch                       |
      | IT       | Enterprise Hardware | VMs                          |
      | IT       | Enterprise Software | Corporate applications       |
      | IT       | Enterprise Software | Database                     |
      | IT       | Enterprise Software | Enterprise resource Planning |
      | IT       | Enterprise Software | Operating system             |
      | IT       | End User Software   | Corporate Applications       |
      | IT       | End User Software   | Office Suite                 |
      | IT       | End User Software   | Operating System             |
      | IT       | End User Software   | Web Browser                  |
      | Accounts | Employee Info       | LTA                          |
      | HR       | Joining             | Welcome Kit allocation       |
      | Admin    | Facility            | Courier                      |


  Scenario Outline: Verify assigned incident ticket creation for other caller with Phone channel and without file upload
    Given Create ticket with file "unattached" and type "Incident", caller "Sheeza Bakshi", channel "Phone Call", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Then Submit and verify ticket with file "unattached" and type "Incident", caller "Sheeza Bakshi", channel "Phone Call", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Examples:
      | dept | category            | subcategory                  |
      | IT   | End User Hardware   | Desktop                      |
      | IT   | End User Hardware   | Deskphones                   |
      | IT   | End User Hardware   | Laptop                       |
      | IT   | End User Hardware   | Docking Station              |
      | IT   | End User Hardware   | Monitor                      |
      | IT   | End User Hardware   | Printer                      |
      | IT   | End User Hardware   | Peripherals                  |
      | IT   | Access and Security | Network Share                |
      | IT   | Access and Security | MFA                          |
      | IT   | Access and Security | Windows                      |
      | IT   | Access and Security | Others                       |
      | IT   | Access and Security | Linux                        |
      | IT   | Enterprise Hardware | Firewall                     |
      | IT   | Enterprise Hardware | Router                       |
      | IT   | Enterprise Hardware | Server                       |
      | IT   | Enterprise Hardware | Switch                       |
      | IT   | Enterprise Hardware | VMs                          |
      | IT   | Enterprise Software | Corporate applications       |
      | IT   | Enterprise Software | Database                     |
      | IT   | Enterprise Software | Enterprise resource Planning |
      | IT   | Enterprise Software | Operating system             |
      | IT   | End User Software   | Corporate Applications       |
      | IT   | End User Software   | Office Suite                 |
      | IT   | End User Software   | Operating System             |
      | IT   | End User Software   | Web Browser                  |


  Scenario Outline: Verify unassigned incident ticket creation for other caller with Teams channel and file upload
    Given Create ticket with file "attached" and type "Incident", caller "Sheeza Bakshi", channel "Teams", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Then Submit and verify ticket with file "attached" and type "Incident", caller "Sheeza Bakshi", channel "Teams", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Examples:
      | dept     | category            | subcategory                  |
      | IT       | End User Hardware   | Desktop                      |
      | IT       | End User Hardware   | Deskphones                   |
      | IT       | End User Hardware   | Laptop                       |
      | IT       | End User Hardware   | Docking Station              |
      | IT       | End User Hardware   | Monitor                      |
      | IT       | End User Hardware   | Printer                      |
      | IT       | End User Hardware   | Peripherals                  |
      | IT       | Access and Security | Network Share                |
      | IT       | Access and Security | MFA                          |
      | IT       | Access and Security | Windows                      |
      | IT       | Access and Security | Others                       |
      | IT       | Access and Security | Linux                        |
      | IT       | Enterprise Hardware | Firewall                     |
      | IT       | Enterprise Hardware | Router                       |
      | IT       | Enterprise Hardware | Server                       |
      | IT       | Enterprise Hardware | Switch                       |
      | IT       | Enterprise Hardware | VMs                          |
      | IT       | Enterprise Software | Corporate applications       |
      | IT       | Enterprise Software | Database                     |
      | IT       | Enterprise Software | Enterprise resource Planning |
      | IT       | Enterprise Software | Operating system             |
      | IT       | End User Software   | Corporate Applications       |
      | IT       | End User Software   | Office Suite                 |
      | IT       | End User Software   | Operating System             |
      | IT       | End User Software   | Web Browser                  |
      | Accounts | Employee Info       | LTA                          |
      | HR       | Joining             | Welcome Kit allocation       |
      | Admin    | Facility            | Courier                      |


  Scenario Outline: Verify assigned incident ticket creation for other caller with Teams channel and file upload
    Given Create ticket with file "attached" and type "Incident", caller "Sheeza Bakshi", channel "Teams", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Then Submit and verify ticket with file "attached" and type "Incident", caller "Sheeza Bakshi", channel "Teams", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Examples:
      | dept | category            | subcategory                  |
      | IT   | End User Hardware   | Desktop                      |
      | IT   | End User Hardware   | Deskphones                   |
      | IT   | End User Hardware   | Laptop                       |
      | IT   | End User Hardware   | Docking Station              |
      | IT   | End User Hardware   | Monitor                      |
      | IT   | End User Hardware   | Printer                      |
      | IT   | End User Hardware   | Peripherals                  |
      | IT   | Access and Security | Network Share                |
      | IT   | Access and Security | MFA                          |
      | IT   | Access and Security | Windows                      |
      | IT   | Access and Security | Others                       |
      | IT   | Access and Security | Linux                        |
      | IT   | Enterprise Hardware | Firewall                     |
      | IT   | Enterprise Hardware | Router                       |
      | IT   | Enterprise Hardware | Server                       |
      | IT   | Enterprise Hardware | Switch                       |
      | IT   | Enterprise Hardware | VMs                          |
      | IT   | Enterprise Software | Corporate applications       |
      | IT   | Enterprise Software | Database                     |
      | IT   | Enterprise Software | Enterprise resource Planning |
      | IT   | Enterprise Software | Operating system             |
      | IT   | End User Software   | Corporate Applications       |
      | IT   | End User Software   | Office Suite                 |
      | IT   | End User Software   | Operating System             |
      | IT   | End User Software   | Web Browser                  |

  Scenario Outline: Verify unassigned incident ticket creation for other caller with Teams channel and without file upload
    Given Create ticket with file "unattached" and type "Incident", caller "Sheeza Bakshi", channel "Teams", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Then Submit and verify ticket with file "unattached" and type "Incident", caller "Sheeza Bakshi", channel "Teams", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Examples:
      | dept     | category            | subcategory                  |
      | IT       | End User Hardware   | Desktop                      |
      | IT       | End User Hardware   | Deskphones                   |
      | IT       | End User Hardware   | Laptop                       |
      | IT       | End User Hardware   | Docking Station              |
      | IT       | End User Hardware   | Monitor                      |
      | IT       | End User Hardware   | Printer                      |
      | IT       | End User Hardware   | Peripherals                  |
      | IT       | Access and Security | Network Share                |
      | IT       | Access and Security | MFA                          |
      | IT       | Access and Security | Windows                      |
      | IT       | Access and Security | Others                       |
      | IT       | Access and Security | Linux                        |
      | IT       | Enterprise Hardware | Firewall                     |
      | IT       | Enterprise Hardware | Router                       |
      | IT       | Enterprise Hardware | Server                       |
      | IT       | Enterprise Hardware | Switch                       |
      | IT       | Enterprise Hardware | VMs                          |
      | IT       | Enterprise Software | Corporate applications       |
      | IT       | Enterprise Software | Database                     |
      | IT       | Enterprise Software | Enterprise resource Planning |
      | IT       | Enterprise Software | Operating system             |
      | IT       | End User Software   | Corporate Applications       |
      | IT       | End User Software   | Office Suite                 |
      | IT       | End User Software   | Operating System             |
      | IT       | End User Software   | Web Browser                  |
      | Accounts | Employee Info       | LTA                          |
      | HR       | Joining             | Welcome Kit allocation       |
      | Admin    | Facility            | Courier                      |


  Scenario Outline: Verify assigned incident ticket creation for other caller with Teams channel and without file upload
    Given Create ticket with file "unattached" and type "Incident", caller "Sheeza Bakshi", channel "Teams", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Then Submit and verify ticket with file "unattached" and type "Incident", caller "Sheeza Bakshi", channel "Teams", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Examples:
      | dept | category            | subcategory                  |
      | IT   | End User Hardware   | Desktop                      |
      | IT   | End User Hardware   | Deskphones                   |
      | IT   | End User Hardware   | Laptop                       |
      | IT   | End User Hardware   | Docking Station              |
      | IT   | End User Hardware   | Monitor                      |
      | IT   | End User Hardware   | Printer                      |
      | IT   | End User Hardware   | Peripherals                  |
      | IT   | Access and Security | Network Share                |
      | IT   | Access and Security | MFA                          |
      | IT   | Access and Security | Windows                      |
      | IT   | Access and Security | Others                       |
      | IT   | Access and Security | Linux                        |
      | IT   | Enterprise Hardware | Firewall                     |
      | IT   | Enterprise Hardware | Router                       |
      | IT   | Enterprise Hardware | Server                       |
      | IT   | Enterprise Hardware | Switch                       |
      | IT   | Enterprise Hardware | VMs                          |
      | IT   | Enterprise Software | Corporate applications       |
      | IT   | Enterprise Software | Database                     |
      | IT   | Enterprise Software | Enterprise resource Planning |
      | IT   | Enterprise Software | Operating system             |
      | IT   | End User Software   | Corporate Applications       |
      | IT   | End User Software   | Office Suite                 |
      | IT   | End User Software   | Operating System             |
      | IT   | End User Software   | Web Browser                  |


  Scenario Outline: Verify unassigned incident ticket creation for other caller with Email channel and file upload
    Given Create ticket with file "attached" and type "Incident", caller "Sheeza Bakshi", channel "Email", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Then Submit and verify ticket with file "attached" and type "Incident", caller "Sheeza Bakshi", channel "Email", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Examples:
      | dept     | category            | subcategory                  |
      | IT       | End User Hardware   | Desktop                      |
      | IT       | End User Hardware   | Deskphones                   |
      | IT       | End User Hardware   | Laptop                       |
      | IT       | End User Hardware   | Docking Station              |
      | IT       | End User Hardware   | Monitor                      |
      | IT       | End User Hardware   | Printer                      |
      | IT       | End User Hardware   | Peripherals                  |
      | IT       | Access and Security | Network Share                |
      | IT       | Access and Security | MFA                          |
      | IT       | Access and Security | Windows                      |
      | IT       | Access and Security | Others                       |
      | IT       | Access and Security | Linux                        |
      | IT       | Enterprise Hardware | Firewall                     |
      | IT       | Enterprise Hardware | Router                       |
      | IT       | Enterprise Hardware | Server                       |
      | IT       | Enterprise Hardware | Switch                       |
      | IT       | Enterprise Hardware | VMs                          |
      | IT       | Enterprise Software | Corporate applications       |
      | IT       | Enterprise Software | Database                     |
      | IT       | Enterprise Software | Enterprise resource Planning |
      | IT       | Enterprise Software | Operating system             |
      | IT       | End User Software   | Corporate Applications       |
      | IT       | End User Software   | Office Suite                 |
      | IT       | End User Software   | Operating System             |
      | IT       | End User Software   | Web Browser                  |
      | Accounts | Employee Info       | LTA                          |
      | HR       | Joining             | Welcome Kit allocation       |
      | Admin    | Facility            | Courier                      |


  Scenario Outline: Verify assigned incident ticket creation for other caller with Email channel and file upload
    Given Create ticket with file "attached" and type "Incident", caller "Sheeza Bakshi", channel "Email", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Then Submit and verify ticket with file "attached" and type "Incident", caller "Sheeza Bakshi", channel "Email", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Examples:
      | dept | category            | subcategory                  |
      | IT   | End User Hardware   | Desktop                      |
      | IT   | End User Hardware   | Deskphones                   |
      | IT   | End User Hardware   | Laptop                       |
      | IT   | End User Hardware   | Docking Station              |
      | IT   | End User Hardware   | Monitor                      |
      | IT   | End User Hardware   | Printer                      |
      | IT   | End User Hardware   | Peripherals                  |
      | IT   | Access and Security | Network Share                |
      | IT   | Access and Security | MFA                          |
      | IT   | Access and Security | Windows                      |
      | IT   | Access and Security | Others                       |
      | IT   | Access and Security | Linux                        |
      | IT   | Enterprise Hardware | Firewall                     |
      | IT   | Enterprise Hardware | Router                       |
      | IT   | Enterprise Hardware | Server                       |
      | IT   | Enterprise Hardware | Switch                       |
      | IT   | Enterprise Hardware | VMs                          |
      | IT   | Enterprise Software | Corporate applications       |
      | IT   | Enterprise Software | Database                     |
      | IT   | Enterprise Software | Enterprise resource Planning |
      | IT   | Enterprise Software | Operating system             |
      | IT   | End User Software   | Corporate Applications       |
      | IT   | End User Software   | Office Suite                 |
      | IT   | End User Software   | Operating System             |
      | IT   | End User Software   | Web Browser                  |

  Scenario Outline: Verify unassigned incident ticket creation for other caller with Email channel and without file upload
    Given Create ticket with file "unattached" and type "Incident", caller "Sheeza Bakshi", channel "Email", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Then Submit and verify ticket with file "unattached" and type "Incident", caller "Sheeza Bakshi", channel "Email", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Examples:
      | dept     | category            | subcategory                  |
      | IT       | End User Hardware   | Desktop                      |
      | IT       | End User Hardware   | Deskphones                   |
      | IT       | End User Hardware   | Laptop                       |
      | IT       | End User Hardware   | Docking Station              |
      | IT       | End User Hardware   | Monitor                      |
      | IT       | End User Hardware   | Printer                      |
      | IT       | End User Hardware   | Peripherals                  |
      | IT       | Access and Security | Network Share                |
      | IT       | Access and Security | MFA                          |
      | IT       | Access and Security | Windows                      |
      | IT       | Access and Security | Others                       |
      | IT       | Access and Security | Linux                        |
      | IT       | Enterprise Hardware | Firewall                     |
      | IT       | Enterprise Hardware | Router                       |
      | IT       | Enterprise Hardware | Server                       |
      | IT       | Enterprise Hardware | Switch                       |
      | IT       | Enterprise Hardware | VMs                          |
      | IT       | Enterprise Software | Corporate applications       |
      | IT       | Enterprise Software | Database                     |
      | IT       | Enterprise Software | Enterprise resource Planning |
      | IT       | Enterprise Software | Operating system             |
      | IT       | End User Software   | Corporate Applications       |
      | IT       | End User Software   | Office Suite                 |
      | IT       | End User Software   | Operating System             |
      | IT       | End User Software   | Web Browser                  |
      | Accounts | Employee Info       | LTA                          |
      | HR       | Joining             | Welcome Kit allocation       |
      | Admin    | Facility            | Courier                      |


  Scenario Outline: Verify assigned incident ticket creation for other caller with Email channel and without file upload
    Given Create ticket with file "unattached" and type "Incident", caller "Sheeza Bakshi", channel "Email", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Then Submit and verify ticket with file "unattached" and type "Incident", caller "Sheeza Bakshi", channel "Email", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Examples:
      | dept | category            | subcategory                  |
      | IT   | End User Hardware   | Desktop                      |
      | IT   | End User Hardware   | Deskphones                   |
      | IT   | End User Hardware   | Laptop                       |
      | IT   | End User Hardware   | Docking Station              |
      | IT   | End User Hardware   | Monitor                      |
      | IT   | End User Hardware   | Printer                      |
      | IT   | End User Hardware   | Peripherals                  |
      | IT   | Access and Security | Network Share                |
      | IT   | Access and Security | MFA                          |
      | IT   | Access and Security | Windows                      |
      | IT   | Access and Security | Others                       |
      | IT   | Access and Security | Linux                        |
      | IT   | Enterprise Hardware | Firewall                     |
      | IT   | Enterprise Hardware | Router                       |
      | IT   | Enterprise Hardware | Server                       |
      | IT   | Enterprise Hardware | Switch                       |
      | IT   | Enterprise Hardware | VMs                          |
      | IT   | Enterprise Software | Corporate applications       |
      | IT   | Enterprise Software | Database                     |
      | IT   | Enterprise Software | Enterprise resource Planning |
      | IT   | Enterprise Software | Operating system             |
      | IT   | End User Software   | Corporate Applications       |
      | IT   | End User Software   | Office Suite                 |
      | IT   | End User Software   | Operating System             |
      | IT   | End User Software   | Web Browser                  |


  Scenario Outline: Verify unassigned request ticket creation for own caller with file upload
    Given Create ticket with file "attached" and type "Request", caller "Abhishek Gautam", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Then Submit and verify ticket with file "attached" and type "Request", caller "Abhishek Gautam", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Examples:
      | dept     | category       | subcategory                    |
      | IT       | Software       | Software License               |
      | IT       | Software       | Unlicensed/OpenSource          |
      | IT       | Software       | Domain Change                  |
      | IT       | Access Request | Creation Of DL/o365 group      |
      | IT       | Access Request | Modification of DL/o365 group  |
      | IT       | Access Request | Modification of security group |
      | IT       | Access Request | New Security group             |
      | IT       | Hardware       | Docking station                |
      | IT       | Hardware       | Keyboard                       |
      | IT       | Hardware       | Mouse                          |
      | IT       | Hardware       | Monitor                        |
      | IT       | Hardware       | RAM                            |
      | IT       | Hardware       | Laptop                         |
      | IT       | Hardware       | Other                          |
      | Accounts | Travel         | Travel cards                   |
      | HR       | MIS            | MIS                            |
      | Admin    | Facility       | Chair                          |


  Scenario Outline: Verify assigned request ticket creation for own caller with file upload
    Given Create ticket with file "attached" and type "Request", caller "Abhishek Gautam", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Then Submit and verify ticket with file "attached" and type "Request", caller "Abhishek Gautam", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Examples:
      | dept | category       | subcategory                    |
      | IT   | Software       | Software License               |
      | IT   | Software       | Unlicensed/OpenSource          |
      | IT   | Software       | Domain Change                  |
      | IT   | Access Request | Creation Of DL/o365 group      |
      | IT   | Access Request | Modification of DL/o365 group  |
      | IT   | Access Request | Modification of security group |
      | IT   | Access Request | New Security group             |
      | IT   | Hardware       | Docking station                |
      | IT   | Hardware       | Keyboard                       |
      | IT   | Hardware       | Mouse                          |
      | IT   | Hardware       | Monitor                        |
      | IT   | Hardware       | RAM                            |
      | IT   | Hardware       | Laptop                         |
      | IT   | Hardware       | Other                          |

  Scenario Outline: Verify unassigned request ticket creation for own caller without file upload
    Given Create ticket with file "unattached" and type "Request", caller "Abhishek Gautam", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Then Submit and verify ticket with file "unattached" and type "Request", caller "Abhishek Gautam", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Examples:
      | dept     | category       | subcategory                    |
      | IT       | Software       | Software License               |
      | IT       | Software       | Unlicensed/OpenSource          |
      | IT       | Software       | Domain Change                  |
      | IT       | Access Request | Creation Of DL/o365 group      |
      | IT       | Access Request | Modification of DL/o365 group  |
      | IT       | Access Request | Modification of security group |
      | IT       | Access Request | New Security group             |
      | IT       | Hardware       | Docking station                |
      | IT       | Hardware       | Keyboard                       |
      | IT       | Hardware       | Mouse                          |
      | IT       | Hardware       | Monitor                        |
      | IT       | Hardware       | RAM                            |
      | IT       | Hardware       | Laptop                         |
      | IT       | Hardware       | Other                          |
      | Accounts | Travel         | Travel cards                   |
      | HR       | MIS            | MIS                            |
      | Admin    | Facility       | Chair                          |

  Scenario Outline: Verify assigned request ticket creation for own caller without file upload
    Given Create ticket with file "unattached" and type "Request", caller "Abhishek Gautam", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Then Submit and verify ticket with file "unattached" and type "Request", caller "Abhishek Gautam", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Examples:
      | dept | category       | subcategory                    |
      | IT   | Software       | Software License               |
      | IT   | Software       | Unlicensed/OpenSource          |
      | IT   | Software       | Domain Change                  |
      | IT   | Access Request | Creation Of DL/o365 group      |
      | IT   | Access Request | Modification of DL/o365 group  |
      | IT   | Access Request | Modification of security group |
      | IT   | Access Request | New Security group             |
      | IT   | Hardware       | Docking station                |
      | IT   | Hardware       | Keyboard                       |
      | IT   | Hardware       | Mouse                          |
      | IT   | Hardware       | Monitor                        |
      | IT   | Hardware       | RAM                            |
      | IT   | Hardware       | Laptop                         |
      | IT   | Hardware       | Other                          |

  Scenario Outline: Verify unassigned request ticket creation for other caller with file upload
    Given Create ticket with file "attached" and type "Request", caller "Sheeza Bakshi", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Then Submit and verify ticket with file "attached" and type "Request", caller "Sheeza Bakshi", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Examples:
      | dept     | category       | subcategory                    |
      | IT       | Software       | Software License               |
      | IT       | Software       | Unlicensed/OpenSource          |
      | IT       | Software       | Domain Change                  |
      | IT       | Access Request | Creation Of DL/o365 group      |
      | IT       | Access Request | Modification of DL/o365 group  |
      | IT       | Access Request | Modification of security group |
      | IT       | Access Request | New Security group             |
      | IT       | Hardware       | Docking station                |
      | IT       | Hardware       | Keyboard                       |
      | IT       | Hardware       | Mouse                          |
      | IT       | Hardware       | Monitor                        |
      | IT       | Hardware       | RAM                            |
      | IT       | Hardware       | Laptop                         |
      | IT       | Hardware       | Other                          |
      | Accounts | Travel         | Travel cards                   |
      | HR       | MIS            | MIS                            |
      | Admin    | Facility       | Chair                          |

  Scenario Outline: Verify assigned request ticket creation for other caller with file upload
    Given Create ticket with file "attached" and type "Request", caller "Sheeza Bakshi", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Then Submit and verify ticket with file "attached" and type "Request", caller "Sheeza Bakshi", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Examples:
      | dept | category       | subcategory                    |
      | IT   | Software       | Software License               |
      | IT   | Software       | Unlicensed/OpenSource          |
      | IT   | Software       | Domain Change                  |
      | IT   | Access Request | Creation Of DL/o365 group      |
      | IT   | Access Request | Modification of DL/o365 group  |
      | IT   | Access Request | Modification of security group |
      | IT   | Access Request | New Security group             |
      | IT   | Hardware       | Docking station                |
      | IT   | Hardware       | Keyboard                       |
      | IT   | Hardware       | Mouse                          |
      | IT   | Hardware       | Monitor                        |
      | IT   | Hardware       | RAM                            |
      | IT   | Hardware       | Laptop                         |
      | IT   | Hardware       | Other                          |

  Scenario Outline: Verify unassigned request ticket creation for other caller without file upload
    Given Create ticket with file "unattached" and type "Request", caller "Sheeza Bakshi", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Then Submit and verify ticket with file "unattached" and type "Request", caller "Sheeza Bakshi", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Examples:
      | dept     | category       | subcategory                    |
      | IT       | Software       | Software License               |
      | IT       | Software       | Unlicensed/OpenSource          |
      | IT       | Software       | Domain Change                  |
      | IT       | Access Request | Creation Of DL/o365 group      |
      | IT       | Access Request | Modification of DL/o365 group  |
      | IT       | Access Request | Modification of security group |
      | IT       | Access Request | New Security group             |
      | IT       | Hardware       | Docking station                |
      | IT       | Hardware       | Keyboard                       |
      | IT       | Hardware       | Mouse                          |
      | IT       | Hardware       | Monitor                        |
      | IT       | Hardware       | RAM                            |
      | IT       | Hardware       | Laptop                         |
      | IT       | Hardware       | Other                          |
      | Accounts | Travel         | Travel cards                   |
      | HR       | MIS            | MIS                            |
      | Admin    | Facility       | Chair                          |

  Scenario Outline: Verify assigned request ticket creation for other caller without file upload
    Given Create ticket with file "unattached" and type "Request", caller "Sheeza Bakshi", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Then Submit and verify ticket with file "unattached" and type "Request", caller "Sheeza Bakshi", channel "Portal", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Examples:
      | dept | category       | subcategory                    |
      | IT   | Software       | Software License               |
      | IT   | Software       | Unlicensed/OpenSource          |
      | IT   | Software       | Domain Change                  |
      | IT   | Access Request | Creation Of DL/o365 group      |
      | IT   | Access Request | Modification of DL/o365 group  |
      | IT   | Access Request | Modification of security group |
      | IT   | Access Request | New Security group             |
      | IT   | Hardware       | Docking station                |
      | IT   | Hardware       | Keyboard                       |
      | IT   | Hardware       | Mouse                          |
      | IT   | Hardware       | Monitor                        |
      | IT   | Hardware       | RAM                            |
      | IT   | Hardware       | Laptop                         |
      | IT   | Hardware       | Other                          |

  Scenario Outline: Verify unassigned request ticket creation for other caller with Phone channel and file upload
    Given Create ticket with file "attached" and type "Request", caller "Sheeza Bakshi", channel "Phone Call", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Then Submit and verify ticket with file "attached" and type "Request", caller "Sheeza Bakshi", channel "Phone Call", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Examples:
      | dept     | category       | subcategory                    |
      | IT       | Software       | Software License               |
      | IT       | Software       | Unlicensed/OpenSource          |
      | IT       | Software       | Domain Change                  |
      | IT       | Access Request | Creation Of DL/o365 group      |
      | IT       | Access Request | Modification of DL/o365 group  |
      | IT       | Access Request | Modification of security group |
      | IT       | Access Request | New Security group             |
      | IT       | Hardware       | Docking station                |
      | IT       | Hardware       | Keyboard                       |
      | IT       | Hardware       | Mouse                          |
      | IT       | Hardware       | Monitor                        |
      | IT       | Hardware       | RAM                            |
      | IT       | Hardware       | Laptop                         |
      | IT       | Hardware       | Other                          |
      | Accounts | Travel         | Travel cards                   |
      | HR       | MIS            | MIS                            |
      | Admin    | Facility       | Chair                          |

  Scenario Outline: Verify assigned request ticket creation for other caller with Phone channel and file upload
    Given Create ticket with file "attached" and type "Request", caller "Sheeza Bakshi", channel "Phone Call", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Then Submit and verify ticket with file "attached" and type "Request", caller "Sheeza Bakshi", channel "Phone Call", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Examples:
      | dept | category       | subcategory                    |
      | IT   | Software       | Software License               |
      | IT   | Software       | Unlicensed/OpenSource          |
      | IT   | Software       | Domain Change                  |
      | IT   | Access Request | Creation Of DL/o365 group      |
      | IT   | Access Request | Modification of DL/o365 group  |
      | IT   | Access Request | Modification of security group |
      | IT   | Access Request | New Security group             |
      | IT   | Hardware       | Docking station                |
      | IT   | Hardware       | Keyboard                       |
      | IT   | Hardware       | Mouse                          |
      | IT   | Hardware       | Monitor                        |
      | IT   | Hardware       | RAM                            |
      | IT   | Hardware       | Laptop                         |
      | IT   | Hardware       | Other                          |

  Scenario Outline: Verify unassigned request ticket creation for other caller with Phone channel and without file upload
    Given Create ticket with file "unattached" and type "Request", caller "Sheeza Bakshi", channel "Phone Call", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Then Submit and verify ticket with file "unattached" and type "Request", caller "Sheeza Bakshi", channel "Phone Call", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Examples:
      | dept     | category       | subcategory                    |
      | IT       | Software       | Software License               |
      | IT       | Software       | Unlicensed/OpenSource          |
      | IT       | Software       | Domain Change                  |
      | IT       | Access Request | Creation Of DL/o365 group      |
      | IT       | Access Request | Modification of DL/o365 group  |
      | IT       | Access Request | Modification of security group |
      | IT       | Access Request | New Security group             |
      | IT       | Hardware       | Docking station                |
      | IT       | Hardware       | Keyboard                       |
      | IT       | Hardware       | Mouse                          |
      | IT       | Hardware       | Monitor                        |
      | IT       | Hardware       | RAM                            |
      | IT       | Hardware       | Laptop                         |
      | IT       | Hardware       | Other                          |
      | Accounts | Travel         | Travel cards                   |
      | HR       | MIS            | MIS                            |
      | Admin    | Facility       | Chair                          |

  Scenario Outline: Verify assigned request ticket creation for other caller with Phone channel and without file upload
    Given Create ticket with file "unattached" and type "Request", caller "Sheeza Bakshi", channel "Phone Call", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Then Submit and verify ticket with file "unattached" and type "Request", caller "Sheeza Bakshi", channel "Phone Call", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Examples:
      | dept | category       | subcategory                    |
      | IT   | Software       | Software License               |
      | IT   | Software       | Unlicensed/OpenSource          |
      | IT   | Software       | Domain Change                  |
      | IT   | Access Request | Creation Of DL/o365 group      |
      | IT   | Access Request | Modification of DL/o365 group  |
      | IT   | Access Request | Modification of security group |
      | IT   | Access Request | New Security group             |
      | IT   | Hardware       | Docking station                |
      | IT   | Hardware       | Keyboard                       |
      | IT   | Hardware       | Mouse                          |
      | IT   | Hardware       | Monitor                        |
      | IT   | Hardware       | RAM                            |
      | IT   | Hardware       | Laptop                         |
      | IT   | Hardware       | Other                          |

  Scenario Outline: Verify unassigned request ticket creation for other caller with Teams channel and file upload
    Given Create ticket with file "attached" and type "Request", caller "Sheeza Bakshi", channel "Teams", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Then Submit and verify ticket with file "attached" and type "Request", caller "Sheeza Bakshi", channel "Teams", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Examples:
      | dept     | category       | subcategory                    |
      | IT       | Software       | Software License               |
      | IT       | Software       | Unlicensed/OpenSource          |
      | IT       | Software       | Domain Change                  |
      | IT       | Access Request | Creation Of DL/o365 group      |
      | IT       | Access Request | Modification of DL/o365 group  |
      | IT       | Access Request | Modification of security group |
      | IT       | Access Request | New Security group             |
      | IT       | Hardware       | Docking station                |
      | IT       | Hardware       | Keyboard                       |
      | IT       | Hardware       | Mouse                          |
      | IT       | Hardware       | Monitor                        |
      | IT       | Hardware       | RAM                            |
      | IT       | Hardware       | Laptop                         |
      | IT       | Hardware       | Other                          |
      | Accounts | Travel         | Travel cards                   |
      | HR       | MIS            | MIS                            |
      | Admin    | Facility       | Chair                          |

  Scenario Outline: Verify assigned request ticket creation for other caller with Teams channel and file upload
    Given Create ticket with file "attached" and type "Request", caller "Sheeza Bakshi", channel "Teams", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Then Submit and verify ticket with file "attached" and type "Request", caller "Sheeza Bakshi", channel "Teams", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Examples:
      | dept | category       | subcategory                    |
      | IT   | Software       | Software License               |
      | IT   | Software       | Unlicensed/OpenSource          |
      | IT   | Software       | Domain Change                  |
      | IT   | Access Request | Creation Of DL/o365 group      |
      | IT   | Access Request | Modification of DL/o365 group  |
      | IT   | Access Request | Modification of security group |
      | IT   | Access Request | New Security group             |
      | IT   | Hardware       | Docking station                |
      | IT   | Hardware       | Keyboard                       |
      | IT   | Hardware       | Mouse                          |
      | IT   | Hardware       | Monitor                        |
      | IT   | Hardware       | RAM                            |
      | IT   | Hardware       | Laptop                         |
      | IT   | Hardware       | Other                          |

  Scenario Outline: Verify unassigned request ticket creation for other caller with Teams channel and without file upload
    Given Create ticket with file "unattached" and type "Request", caller "Sheeza Bakshi", channel "Teams", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Then Submit and verify ticket with file "unattached" and type "Request", caller "Sheeza Bakshi", channel "Teams", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Examples:
      | dept     | category       | subcategory                    |
      | IT       | Software       | Software License               |
      | IT       | Software       | Unlicensed/OpenSource          |
      | IT       | Software       | Domain Change                  |
      | IT       | Access Request | Creation Of DL/o365 group      |
      | IT       | Access Request | Modification of DL/o365 group  |
      | IT       | Access Request | Modification of security group |
      | IT       | Access Request | New Security group             |
      | IT       | Hardware       | Docking station                |
      | IT       | Hardware       | Keyboard                       |
      | IT       | Hardware       | Mouse                          |
      | IT       | Hardware       | Monitor                        |
      | IT       | Hardware       | RAM                            |
      | IT       | Hardware       | Laptop                         |
      | IT       | Hardware       | Other                          |
      | Accounts | Travel         | Travel cards                   |
      | HR       | MIS            | MIS                            |
      | Admin    | Facility       | Chair                          |

  Scenario Outline: Verify assigned request ticket creation for other caller with Teams channel and without file upload
    Given Create ticket with file "unattached" and type "Request", caller "Sheeza Bakshi", channel "Teams", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Then Submit and verify ticket with file "unattached" and type "Request", caller "Sheeza Bakshi", channel "Teams", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Examples:
      | dept | category       | subcategory                    |
      | IT   | Software       | Software License               |
      | IT   | Software       | Unlicensed/OpenSource          |
      | IT   | Software       | Domain Change                  |
      | IT   | Access Request | Creation Of DL/o365 group      |
      | IT   | Access Request | Modification of DL/o365 group  |
      | IT   | Access Request | Modification of security group |
      | IT   | Access Request | New Security group             |
      | IT   | Hardware       | Docking station                |
      | IT   | Hardware       | Keyboard                       |
      | IT   | Hardware       | Mouse                          |
      | IT   | Hardware       | Monitor                        |
      | IT   | Hardware       | RAM                            |
      | IT   | Hardware       | Laptop                         |
      | IT   | Hardware       | Other                          |

  Scenario Outline: Verify unassigned request ticket creation for other caller with Email channel and file upload
    Given Create ticket with file "attached" and type "Request", caller "Sheeza Bakshi", channel "Email", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Then Submit and verify ticket with file "attached" and type "Request", caller "Sheeza Bakshi", channel "Email", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Examples:
      | dept     | category       | subcategory                    |
      | IT       | Software       | Software License               |
      | IT       | Software       | Unlicensed/OpenSource          |
      | IT       | Software       | Domain Change                  |
      | IT       | Access Request | Creation Of DL/o365 group      |
      | IT       | Access Request | Modification of DL/o365 group  |
      | IT       | Access Request | Modification of security group |
      | IT       | Access Request | New Security group             |
      | IT       | Hardware       | Docking station                |
      | IT       | Hardware       | Keyboard                       |
      | IT       | Hardware       | Mouse                          |
      | IT       | Hardware       | Monitor                        |
      | IT       | Hardware       | RAM                            |
      | IT       | Hardware       | Laptop                         |
      | IT       | Hardware       | Other                          |
      | Accounts | Travel         | Travel cards                   |
      | HR       | MIS            | MIS                            |
      | Admin    | Facility       | Chair                          |

  Scenario Outline: Verify assigned request ticket creation for other caller with Email channel and file upload
    Given Create ticket with file "attached" and type "Request", caller "Sheeza Bakshi", channel "Email", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Then Submit and verify ticket with file "attached" and type "Request", caller "Sheeza Bakshi", channel "Email", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Examples:
      | dept | category       | subcategory                    |
      | IT   | Software       | Software License               |
      | IT   | Software       | Unlicensed/OpenSource          |
      | IT   | Software       | Domain Change                  |
      | IT   | Access Request | Creation Of DL/o365 group      |
      | IT   | Access Request | Modification of DL/o365 group  |
      | IT   | Access Request | Modification of security group |
      | IT   | Access Request | New Security group             |
      | IT   | Hardware       | Docking station                |
      | IT   | Hardware       | Keyboard                       |
      | IT   | Hardware       | Mouse                          |
      | IT   | Hardware       | Monitor                        |
      | IT   | Hardware       | RAM                            |
      | IT   | Hardware       | Laptop                         |
      | IT   | Hardware       | Other                          |

  Scenario Outline: Verify unassigned request ticket creation for other caller with Email channel and without file upload
    Given Create ticket with file "unattached" and type "Request", caller "Sheeza Bakshi", channel "Email", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Then Submit and verify ticket with file "unattached" and type "Request", caller "Sheeza Bakshi", channel "Email", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Open"
    Examples:
      | dept     | category       | subcategory                    |
      | IT       | Software       | Software License               |
      | IT       | Software       | Unlicensed/OpenSource          |
      | IT       | Software       | Domain Change                  |
      | IT       | Access Request | Creation Of DL/o365 group      |
      | IT       | Access Request | Modification of DL/o365 group  |
      | IT       | Access Request | Modification of security group |
      | IT       | Access Request | New Security group             |
      | IT       | Hardware       | Docking station                |
      | IT       | Hardware       | Keyboard                       |
      | IT       | Hardware       | Mouse                          |
      | IT       | Hardware       | Monitor                        |
      | IT       | Hardware       | RAM                            |
      | IT       | Hardware       | Laptop                         |
      | IT       | Hardware       | Other                          |
      | Accounts | Travel         | Travel cards                   |
      | HR       | MIS            | MIS                            |
      | Admin    | Facility       | Chair                          |

  Scenario Outline: Verify assigned request ticket creation for other caller with Email channel and without file upload
    Given Create ticket with file "unattached" and type "Request", caller "Sheeza Bakshi", channel "Email", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Then Submit and verify ticket with file "unattached" and type "Request", caller "Sheeza Bakshi", channel "Email", dept "<dept>", category "<category>", sub-category "<subcategory>", subject "support test with file", desc "support test" and status "Assigned"
    Examples:
      | dept | category       | subcategory                    |
      | IT   | Software       | Software License               |
      | IT   | Software       | Unlicensed/OpenSource          |
      | IT   | Software       | Domain Change                  |
      | IT   | Access Request | Creation Of DL/o365 group      |
      | IT   | Access Request | Modification of DL/o365 group  |
      | IT   | Access Request | Modification of security group |
      | IT   | Access Request | New Security group             |
      | IT   | Hardware       | Docking station                |
      | IT   | Hardware       | Keyboard                       |
      | IT   | Hardware       | Mouse                          |
      | IT   | Hardware       | Monitor                        |
      | IT   | Hardware       | RAM                            |
      | IT   | Hardware       | Laptop                         |
      | IT   | Hardware       | Other                          |

  Scenario: Check for field validation in create ticket form
    Given Open create ticket form
    Then Verify subject character limit and required field values

  Scenario: Check comments and files can be added for support incident ticket details
    Given Create ticket with file "unattached" and type "Incident", caller "Abhishek Gautam", channel "Portal", dept "IT", category "End User Hardware", sub-category "Desktop", subject "support test with file", desc "support test" and status "Open"
    Then Submit and verify ticket with file "unattached" and type "Incident", caller "Abhishek Gautam", channel "Portal", dept "IT", category "End User Hardware", sub-category "Desktop", subject "support test with file", desc "support test" and status "Open"
    Then Verify if comment "no file" can be added for the ticket
    Then Validate comment with file uploads on ticket page

  Scenario: Check comments and files can be added for support request ticket details
    Given Create ticket with file "unattached" and type "Request", caller "Abhishek Gautam", channel "Portal", dept "IT", category "Software", sub-category "Software License", subject "support test with file", desc "support test" and status "Open"
    Then Submit and verify ticket with file "unattached" and type "Request", caller "Abhishek Gautam", channel "Portal", dept "IT", category "Software", sub-category "Software License", subject "support test with file", desc "support test" and status "Open"
    Then Verify if comment "no file" can be added for the ticket
    Then Validate comment with file uploads on ticket page

  Scenario: Check file upload with valid extensions and size limit for support incident
    Given Create ticket with file "unattached" and type "Incident", caller "Abhishek Gautam", channel "Portal", dept "IT", category "End User Hardware", sub-category "Desktop", subject "support test with file", desc "support test" and status "Open"
    And Upload file with valid extensions and size
    Then Verify if "valid" file can be uploaded

  Scenario: Check file upload with invalid extensions and size limit for support incident
    Given Create ticket with file "unattached" and type "Incident", caller "Abhishek Gautam", channel "Portal", dept "IT", category "End User Hardware", sub-category "Desktop", subject "support test with file", desc "support test" and status "Open"
    And Upload file with invalid extensions and size
    Then Verify if "invalid" file can be uploaded

  Scenario: Check file upload with valid extensions and size limit for support request
    Given Create ticket with file "unattached" and type "Request", caller "Abhishek Gautam", channel "Portal", dept "IT", category "Software", sub-category "Software License", subject "support test with file", desc "support test" and status "Open"
    And Upload file with valid extensions and size
    Then Verify if "valid" file can be uploaded

  Scenario: Check file upload with invalid extensions and size limit for support request
    Given Create ticket with file "unattached" and type "Request", caller "Abhishek Gautam", channel "Portal", dept "IT", category "Software", sub-category "Software License", subject "support test with file", desc "support test" and status "Open"
    And Upload file with invalid extensions and size
    Then Verify if "invalid" file can be uploaded