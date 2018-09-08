import os
import sys
import base64
import hashlib
import threading
import xbmc
import xbmcgui

def main():
	arguments = {}
	for a in sys.argv[1:]:
		kvp = a.split('=', 1)
		if len(kvp) == 2:
			arguments[kvp[0]] = kvp[1]
		del kvp

	try:
		title = arguments['title']
		text = arguments['message']
		image = base64.b64decode(arguments['image'])
		delay = int(arguments['displaytime'])
	except Exception as e:
		xbmc.log('Notifikator: Failed to retrieve parameters: {0}'.format(e), xbmc.LOGDEBUG)
		return

	del arguments

	if image:
		image_path_special = 'special://temp/notifikator'
		image_path = xbmc.translatePath(image_path_special)

		try:
			os.makedirs(image_path)
		except:
			pass

		image_hash = base64.urlsafe_b64encode(hashlib.sha256(image).digest())
		image_tmp_path = os.path.join(image_path, '{0}.{1}.tmp'.format(image_hash, threading.current_thread().ident))
		image_file_path = os.path.join(image_path, '{0}.png'.format(image_hash))

		if not os.path.isfile(image_file_path):
			with open(image_tmp_path, 'wb+') as image_file:
				image_file.write(image)
			del image

			try:
				os.rename(image_tmp_path, image_file_path)
			except:
				try:
					os.remove(image_tmp_path)
				except:
					pass

		image_url = '{0}/{1}.png'.format(image_path_special, image_hash)

	else:
		image_url = xbmcgui.NOTIFICATION_INFO

	dialog = xbmcgui.Dialog()
	dialog.notification(title, text, image_url, delay, False)
	del dialog

if __name__ == '__main__':
	main()