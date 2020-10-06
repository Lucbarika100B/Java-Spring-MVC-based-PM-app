<html lang="en"><head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>BARIKA login</title>
     <style>
        body {
            background-color: #3e3e3e;
            color :white;
        }
    </style>
    </head>
  <body>
     <div class="container">
      <form class="form-signin" method="post" action="/TesisIII/login">
        <h2 class="form-signin-heading">Welcome to BARIKA</h2>
        <h3 class="form-signin-heading">Please Login</h3>
        <p>
          <label for="username" class="sr-only">Username</label>
          <input type="text" id="username" name="username" class="form-control" placeholder="Username" required="" autofocus="">
        </p>
        <p>
          <label for="password" class="sr-only">Password</label>
          <input type="password" id="password" name="password" class="form-control" placeholder="Password" required="">
        </p>
        <button class="btn btn-primary btn-block" type="submit">Login</button>
      </form>
</div>
</body></html>