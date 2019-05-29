
#revrep
Review Reporting Service

It is a Spring boot application for Reporting Product Reviews. It exposes following rest endpoints:

GET /reviews Used to get List of Reviews for a product

POST /reviews Used to add new Review

POST /reviews/like Used to register a like sentiment for an existing review

Other tools used: DB - review - It uses mongoDB to store review data.

Kafka event publisher - It produces NewReviewAddedEvent to Kafka topic for every new review entry created.

