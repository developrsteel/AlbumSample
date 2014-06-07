AlbumSample
===========

1. Optimize adapter ->
This is done with help of widely used, for this purpose, view holder pattern.
ItemHolder (see code ItemsAdapter.java) stores two text views and one image view
inside the tag filed of the layout. When ItemHolder is initialized/populated, all
three views can be accessed without the need to look them up (findViewById) which
saves processor cycles.

2. Change action bar title on drawer item click ->
Simple communication between Activity and Fragment via communication
interface (NavigationDrawerCallbacks).

3. Change list on drawer item click ->
Simple communication between Fragment and Fragment via Activity and communication
interface (NavigationDrawerCallbacks).

4. Load images from remote server ->
com.sample.album.imgutil.ImageDownloader is implemented for this purpose.
This is another one pattern that is used in Android programming.
With this implementation images are downloaded asynchronously in the background.
When image is downloaded and decoded it is ready to be shown inside image view.
This is some kind of download image on demand, because image are not loaded when list view
is created, instead of that images are loaded when list item becomes visible. In this way
application loading time is reduced and ui is fluent. Images can be cached onto sd card.

5. Adapt application for tablet use (two columns GridView) ->
New Fragment with GridView is created. Flag for layout resources (GridView or ListView) is added
to ItemsAdapter. In AlbumSampleMain (main activity) simple check is added to determine screen
orientation and based on that to choose fragment with grid or list view. Orientation check is
not enough to determine is device phone or tablet, but this is done only by that because of
testing purposes. Screen size and display metrics also need to be checked.