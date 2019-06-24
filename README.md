# java-filesystem

*Design file system to provide following functions:* 
* mkdir: Give full qualifying path, make directory. Create all the directories if they are not existing in the path.
* ls: Given directory path, list all the files and folders in sorted order. Return file/directory names as List of Strings.
* createFile: Given path (directory path excluding filename), a file name and the content of file as a String, create said file. If the file already exists, append given content to original content. This function has void return type. 
* cat: Given directory path and a file name, return file content as a String. 

**Assumption:**
* All paths are absolute paths starting with "/" as root directory.
* All operations are valid operations. Error check needs to be implemented.  
* Directory and File names are case sensitive

**Implementation:**
* Implemented N-ary tree structure to implement in-memory tree structure. 
* Used HashMap to look-up directory/file name in O(1) time.
* Time complexity: 
    * mkdir, takes O(n) time to navigate to desired directory path and create a directory. 
    * ls, takes O(n + k log k) time. Here n is directory hierarchy (depth) and k log k is time complexity to sort k result-set in the directory.
    * createFile, cat, takes O(n) time. It takes O(n) time to navigate directory structure and constant time to create, append or read a file. 
     
**Desired Improvements:**
* Build AI to allow user to input either relative or absolute path. 
* Add more comprehensive tests. 

**Source Code:**
* Create desired project workspace directory. example: ~/Documents/workspace/ 
* Download from git repo: git clone https://github.com/damehta/java-filesystem.git
* Use IDE (Eclipse/Idea) and import as new Maven project.
* Extend and Run tests using mvn clean test. 

