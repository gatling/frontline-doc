# FrontLine Documentation

Node.js dependencies:

```
npm install
```

Running a local server:

```
npm run server
```

Then, connect to http://localhost:1313

Create a new page:

```
npm run create docs/user/test/test.md
```

Make sure you have draft set to false in the page metadata, otherwise the page you created won't be displayed.

### Page weight

The weight indicates which page comes first in the sidebar. We use this system: SBXX0 where:
- S: corresponds to the sidenav section number (begins at 1)
- B: corresponds to the branch number (set to 0 if not in a branch)
- XX: place inside the current group of pages (begins at 1)
