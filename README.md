# AOPEventTracking
a simple example for umeng analytics. Use annotation to send custom event 
--

<br>
```java
    @ActivityResume
    @Override
    protected void onResume() {
        super.onResume();
    }

    @ActivityPause
    @Override
    protected void onPause() {
        super.onPause();
    }

    @EventTracking(eventId = "login")
    private void login() {
        Toast.makeText(this, "login", Toast.LENGTH_SHORT).show();
    }

    @EventTracking(eventId = "share", keys = {"share_type", "share_uri"}, values = {"pic", "https://XXX"})
    private void share() {
        Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
    }
```

```java
    @PageResume(pageName = "DummyFragment")
    @Override
    public void onResume() {
        super.onResume();
    }

    @PageResume(pageName = "DummyFragment")
    @Override
    public void onPause() {
        super.onPause();
    }
```
