#!/bin/bash

# The two variables you must change
version={param1}
repo_url=REPLACE_WITH_YOUR_REPOSITORY_URL

archive_name=frontline-installer-${version}.zip
archive_url=${repo_url}/frontline-installer/${version}/${archive_name}

curl -O ${archive_url}
curl -O ${archive_url}.sha1

echo "$(cat ${archive_name}.sha1)  ${archive_name}" | sha1sum     -c -
# For MacOS users:                                    shasum -a 1 -c -
