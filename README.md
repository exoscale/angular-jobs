jobs: Angular JS demo app, with a clojure API
=============================================

Ultra simple CRUD API, with a client-side,
single-page Angular JS app. The stored model
is meant to handle information for a job board.

The API exposes only 3 routes:

* *GET* `/jobs`: retrieve jobs
* *POST* `/jobs`: create a new job, an ID is assigned
* *DELETE* `/jobs/:id`: deletes a job by ID

The app is served as un-minified static content, sources
are available in the `resources/public` directory.

The Angular application is contained in a single file but
provides a service, controller and router for a single page
application.
