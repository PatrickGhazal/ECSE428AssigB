# In this feature, a 'full email' means an email that contains a subject, a body, and an attachment.

Feature: Gmail

Background:
	Given I am on the Gmail homepage
	And I click Compose

Scenario: Send a full email 1
	When I set Recipient 1 as recipient
	And I set Subject 1 as subject
	And I set Body 1 as body
	And I set Attachment 1 as attachment
	Then I click on Send
	And the email is sent
	
Scenario: Send a full email 2
	When I set Recipient 2 as recipient
	And I set Subject 2 as subject
	And I set Body 2 as body
	And I set Attachment 2 as attachment
	Then I click on Send
	And the email is sent

Scenario: Send a full email 3
	When I set Recipient 1 as recipient
	And I set Subject 2 as subject
	And I set Body 2 as body
	And I set Attachment 3 as attachment
	Then I click on Send
	And the email is sent

Scenario: Send a partial email - no body
	When I set Recipient 1 as recipient
	And I set Subject 1 as subject
	And I set Attachment 2 as attachment
	Then the system sets the empty string as body
	And I Click on Send
	And the email is sent

Scenario: Send a partial email - no subject
	When I set Recipient 2 as recipient
	And I set Body 1 as body
	And I set Attachment 1 as attachment
	Then the system sets the empty string as subject
	And I Click on Send
	And the email is sent

Scenario: Attempt to send with no recipient
	When I set Subject 1 as Subject
	And I set Body 1 as body
	And I set Attachment 1 as attachment
	Then I cannot click on Send
	And the email is not sent

Scenario: Send with no attachment
	When I set Recipient 1 as recipient
	And I set Subject 1 as subject
	And I set Body 1 as body
	Then I click on Send
	And the email is sent with no attachment