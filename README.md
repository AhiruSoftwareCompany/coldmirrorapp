# coldmirrorapp
A soundboard app with quotes and effects from coldmirror (German YouTuber)

## Installing the coldmirrorapp
Download the [coldmirror.apk](https://github.com/dieechtenilente/coldmirrorapp/releases) and copy it to your android phone. Open the file browser and navigate to the apk file. To install it, click on it and accept the warning. Enjoy the app :)

![](https://raw.githubusercontent.com/dieechtenilente/coldmirrorapp/master/screenshots/screenshot1.png)
![](https://raw.githubusercontent.com/dieechtenilente/coldmirrorapp/master/screenshots/screenshot2.png)

## Using the coldmirrorapp
Clicking on a button plays the wanted sound, long press opens a share dialog to share the sound e.g. to a WhatsApp contact. The search on the top righthand corner is intuitive.

## Fork & Clone the repo
If you haven't already, fork [this repo](https://github.com/dieechtenilente/coldmirrorapp/fork).

### Android Studio
Clone the **coldmirror app** in Android Studio:

1. Choose **VCS > Checkout from Version Control > GitHub** on the main menu.
2. From the **Repository** drop-down list, select the source repository to clone the data from.
3. In the **Folder** text box, specify the directory where the local repository for cloned sources will be set up.
4. Click the Clone button to start cloning the sources from the specified remote repository.

### Command line Git
[Clone the coldmirrorapp](https://help.github.com/articles/fork-a-repo#step-2-clone-your-fork)

Open your terminal, navigate to your working directory, use `git clone` to get a local copy of the repo.

```
# Clones your fork of the repository into the current directory in terminal
$ git clone https://github.com/YOUR-USERNAME/coldmirrorapp.git
```

## Configure remote upstream for your fork
To sync changes you make in a fork with this repository, you must configure a remote that points to the upstream repository in Git.

- Open a terminal or command prompt
- List the current configured remote repository for your fork

```
$ git remote -v
origin	https://github.com/YOUR_USERNAME/coldmirrorapp.git (fetch)
origin	https://github.com/YOUR_USERNAME/coldmirrorapp.git (push)
```

- Specify a new remote upstream repository

```
$ git remote add upstream https://github.com/dieechtenilente/coldmirrorapp.git
```

- Verify the new upstream repository

```
$ git remote -v

origin	https://github.com/YOUR_USERNAME/coldmirrorapp.git (fetch)
origin	https://github.com/YOUR_USERNAME/coldmirrorapp.git (push)
upstream https://github.com/dieechtenilente/coldmirrorapp.git (fetch)
upstream https://github.com/dieechtenilente/coldmirrorapp.git (push)
```

### Sync your fork
Once you have set up a remote upstream, you can keep your fork up to date with our repository by syncing your fork.

- Open a terminal or command prompt
- Change to the current working directory of your local repository
- Fetch the branches and commits from the upstream repository.  Commits to `master` will be stored in a local branch, `upstream/master`.

```
$ git fetch upstream
```

- Check out your forks local `master` branch

```
$ git checkout master
```

- Merge changes from `upstream/master` into  your local `master` branch which syncs your forks `master` branch with our repository.

```
$ git merge upstream/master
```

### Import Gradle project into Android Studio
Once the project is cloned to disk you can import into Android Studio:

* From the toolbar select **File > Import Project**, or **Import Non-Android Studio project** from the Welcome Quick Start.
* Navigate to the root project folder, **coldmirrorapp** directory and click **OK**

## Issues
Found a bug? Want to request a new feature?  Please let us know by submitting an issue.

## Contributing
Anyone and everyone is welcome to contribute e.g. sounds (with YouTube source).

## Sources/Adaptions
Readme adapted from [Esri](https://github.com/Esri/arcgis-runtime-samples-android)


### 4 oki
[mp3](https://github.com/dieechtenilente/coldmirrorapp/tree/master/app/src/main/res/raw)
