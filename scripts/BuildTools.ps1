
# Dot source this file:	. .\BuildTools.ps1

function Build-Test {
	[CmdletBinding()]
	param( )

	process {
		$Bin = [IO.Path]::Combine( $PSScriptRoot, '..', 'bin', 'test' )
		$Test = [IO.Path]::Combine( $PSScriptRoot, '..', 'src', 'test', 'java' )
		$Main = [IO.Path]::Combine( $PSScriptRoot, '..', 'src', 'main', 'java' )
		$Lib = [IO.Path]::Combine( $PSScriptRoot, '..', 'lib', '*' )
		$Package = 'degreesmart'

		[void]( Remove-Item -Path $Bin -Recurse -Force -ErrorAction 'SilentlyContinue' )
		[void]( New-Item -Path $Bin -Type 'Directory' -Force -ErrorAction 'Stop' )

		$MainFiles = Get-ChildItem ( Join-Path $Main $Package ) |
			Where-Object Name -like '*.java'
		$FileNames = foreach ( $File in $MainFiles ) {
				Write-Output "${Main}/${Package}/$($File.name)"
			}

		$TestFiles = Get-ChildItem ( Join-Path $Test $Package ) |
			Where-Object Name -like '*.java'
		$FileNames += foreach ( $File in $TestFiles ) {
				Write-Output "${Test}/${Package}/$($File.name)"
			}

		javac -Xlint:unchecked -d $Bin -cp "${Lib};${Bin}" $FileNames

		if ( $LastExitCode -eq 0 ) {
			Write-Verbose -Verbose "Build finished: $Bin"
		} else {
			Write-Error "Build failed!"
		}
	}
}

function Build-DegreeSmart {
	[CmdletBinding()]
	param( )

	process {
		$Bin = [IO.Path]::Combine( $PSScriptRoot, '..', 'bin', 'main' )
		$Src = [IO.Path]::Combine( $PSScriptRoot, '..', 'src', 'main', 'java' )
		$Lib = [IO.Path]::Combine( $PSScriptRoot, '..', 'lib', '*' )
		$Package = 'degreesmart'

		[void]( Remove-Item -Path $Bin -Recurse -Force -ErrorAction 'SilentlyContinue' )
		[void]( New-Item -Path $Bin -Type 'Directory' -Force -ErrorAction 'Stop' )

		$Files = Get-ChildItem ( Join-Path $Src $Package ) |
			Where-Object Name -like '*.java'

		$FileNames = foreach ( $File in $Files ) {
				Write-Output "${Src}/${Package}/$($File.name)"
			}

		javac -Xlint:unchecked -d $Bin -cp "${Lib};${Src}" $FileNames

		if ( $LastExitCode -eq 0 ) {
			Write-Verbose -Verbose "Build finished: $Bin"
		} else {
			Write-Error "Build failed!"
		}
	}
}

function Invoke-DegreeSmart {
	[CmdletBinding()]
	param( )

	process {
		Build-DegreeSmart -ErrorAction 'Stop'

		$Bin = [IO.Path]::Combine( $PSScriptRoot, '..', 'bin', 'main' )
		$Lib = [IO.Path]::Combine( $PSScriptRoot, '..', 'lib', '*' )
		$Package = 'degreesmart'
		$Main = 'DegreeSmartUI'

		java -cp "${Bin};${Lib}" "${Package}.${Main}"
	}
}

function Invoke-Test {
	[CmdletBinding()]
	param( )

	process {
		Build-Test -ErrorAction 'Stop'

		$Bin = [IO.Path]::Combine( $PSScriptRoot, '..', 'bin', 'test' )
		$Lib = [IO.Path]::Combine( $PSScriptRoot, '..', 'lib', '*' )
		$JUnit = 'org.junit.platform.console.ConsoleLauncher'

		java -cp "${Bin};${Lib}" $JUnit execute --scan-classpath
	}
}
