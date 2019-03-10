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